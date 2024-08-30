package pks.example.modernize.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



// @SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
// @ActiveProfiles("h2db")
public class LoanProgramEntityTests {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private LoanProgramRespository loanProgramRespository;

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
        loanProgramRespository.save(testLoanProgram);        
        
        LoanProgramEntity loanProgram = loanProgramRespository.findById(testLoanProgram.getCode()).orElse(null);
        assertNotNull(loanProgram);
        assertEquals(loanProgram.getCode(), testLoanProgram.getCode());
        assertEquals(loanProgram.getName(), testLoanProgram.getName());
        assertEquals(loanProgram, testLoanProgram);
        assertEquals(4,loanProgramRespository.findAll().size());
        
        loanProgramRespository.delete(testLoanProgram);
    }
 
    @Test @Order(100)
    void ExerciseHierarchy() {

        // add some users
        userRepository.save(new UserEntity("shinerp","Peter Shiner"));
        userRepository.save(new UserEntity("smitht", "Thomas Smith"));
        userRepository.save(new UserEntity("jonesh","Jimmy Jone"));
        userRepository.flush();

        // add a program
        loanProgramRespository.save(new LoanProgramEntity("SSJ","SSJ Loan Program","Shiner/Smith/Jones"));
        loanProgramRespository.flush();

        // add a borrower to agree to a loan
        List<LoanProgramEntity> pList = loanProgramRespository.findAnyMatching("Smith");
        assertEquals(1,pList.size());

        LoanProgramEntity pgm = pList.get(0);
        assertTrue(pgm.getCode().equals("SSJ"));

        borrowerRepository.save(new BorrowerEntity(new BorrowerEntityPk("BC1922",pgm.getCode()),"Borrower Tom","7038891657","Highlands Ranch"));
        List<BorrowerEntity> bList = borrowerRepository.listByCode("BC1922");
        assertEquals(1,bList.size());
        assertTrue(bList.get(0).getName().equals("Borrower Tom"));





        // BorrowerEntityPk borrowerPk = new BorrowerEntityPk();

        // BorrowerEntity borrower = new BorrowerEntity();
        // borrower.setName("Peter");
        // borrower.setOrganization("MetaPhase");

        // testLoanProgram = new LoanProgramEntity();
        // testLoanProgram.setCode("MMM-1");
        // loanProgramRespository.save(testLoanProgram);

        // LoanEntity testLoan = new LoanEntity();
        // testLoan.setLoanId(new LoanEntityPk(10,"AAA"));
        // testLoan.setCode("LN-"+count.toString());
        // testLoan.setName("Computer");
        // testLoan.setAmount(new BigDecimal(123423));
        // testLoan.setRate(new BigDecimal(4.375));
        // loanRepository.save(testLoan);

        // count = loanRepository.findAll().size();

        
        // LoanEntity loan = loanRepository.findById(new LoanEntityPk(1,"AAA")).orElse(null);
        // assertNotNull(loan);
        // assertEquals("AAA",loan.loanId.getProgram());
        // assertEquals(1,loan.loanId.getId());

        // if (loan != null) {}
        //     loanRepository.delete(loan);
    }

}
