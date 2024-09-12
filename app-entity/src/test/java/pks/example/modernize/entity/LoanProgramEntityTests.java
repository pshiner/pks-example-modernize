package pks.example.modernize.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
// @ActiveProfiles("h2db")
public class LoanProgramEntityTests {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private LoanProgramRepository loanProgramRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanScheduleRepository loanScheduleRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;


    private LoanProgramEntity testLoanProgram;
    private Integer count = 0;

    // @BeforeEach
    // public void setUp() {

    // }

    // @AfterEach
    // public void tearDown() {
    // }

    @Test @Order(000)
    void ExerciseLoanProgramRespository() {
        testLoanProgram = new LoanProgramEntity();
        testLoanProgram.setCode("ASD-"+count.toString());
        testLoanProgram.setName("my big name");
        testLoanProgram.setDescription("this is a long description");
        loanProgramRepository.save(testLoanProgram);        
        
        LoanProgramEntity loanProgram = loanProgramRepository.findById(testLoanProgram.getCode()).orElse(null);
        assertNotNull(loanProgram);
        assertEquals(loanProgram.getCode(), testLoanProgram.getCode());
        assertEquals(loanProgram.getName(), testLoanProgram.getName());
        assertEquals(loanProgram, testLoanProgram);
        assertEquals(2,loanProgramRepository.findAll().size());
        // #TODO:  Why is this a two (2) instead of a (1) ??  The findAll() is counting the returned entity that is the same as the saved enity??
        loanProgramRepository.delete(testLoanProgram);
    }
 
    @Test @Order(100)
    void ExerciseHierarchy() {

        // add some users
        userRepository.save(new UserEntity("shinerp","Peter Shiner"));
        userRepository.save(new UserEntity("smitht", "Thomas Smith"));
        userRepository.save(new UserEntity("jonesh","Jimmy Jone"));
        userRepository.flush();

        // add a program
        loanProgramRepository.save(new LoanProgramEntity("SSJ","SSJ Loan Program","Shiner/Smith/Jones"));
        loanProgramRepository.flush();

        // add a borrower to agree to a loan
        List<LoanProgramEntity> pList = loanProgramRepository.findAnyMatching("Smith");
        assertEquals(1,pList.size());

        LoanProgramEntity pgm = pList.get(0);
        assertTrue(pgm.getCode().equals("SSJ"));

        borrowerRepository.save(new BorrowerEntity(new BorrowerEntityPk("BC1922",pgm.getCode()),"Borrower Tom","7038891657","Highlands Ranch"));
        List<BorrowerEntity> bList = borrowerRepository.listByCode("BC1922");
        assertEquals(1,bList.size());
        assertTrue(bList.get(0).getName().equals("Borrower Tom"));
        BorrowerEntity bwr = bList.get(0);


        UserEntity usr = userRepository.findById("smitht").orElse(null);
        assertNotNull(usr);

        LoanEntity loan = new LoanEntity();
        loan.setPk(new LoanEntityPk("LLL001",pgm.getCode()));
        loan.setAmount(new BigDecimal(39283));
        loan.setBorrowerCode(bwr.getPk().getCode());
        loan.setDescription("My New Loan");
        loan.setLoanNumber(16);
        loan.setName("Loan # 16");
        loan.setRate(new BigDecimal(12.325));
        loan.setAppUser(usr.getUsername());
        loanRepository.save(loan);
        
        for (int count = 0; count < 10; count++) {
            LoanSchedulePk pmtPk= new LoanSchedulePk();
            pmtPk.setLoanCode(loan.pk.getCode());
            pmtPk.setProgramCode(loan.pk.getProgram());
            pmtPk.setSequence(count);

            LoanScheduleEntity pmt = new LoanScheduleEntity();
            pmt.setPk(pmtPk);
            pmt.setBalCapitalizedInterest(new BigDecimal(0));
            pmt.setBalPrincipal(new BigDecimal(230912));
            pmt.setPmtDate(new Date(System.currentTimeMillis()));
            loanScheduleRepository.save(pmt);
        }

    }

}
