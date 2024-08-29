package pks.example.modernize.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
// @ActiveProfiles("h2db")
public class LoanProgramEntityTests {

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

    @Test
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
/* 
    @Test
    void ExerciseHierarchy() {



        BorrowerEntityPk borrowerPk = new BorrowerEntityPk();

        BorrowerEntity borrower = new BorrowerEntity();
        borrower.setName("Peter");
        borrower.setOrganization("MetaPhase");");

        testLoanProgram = new LoanProgramEntity();
        testLoanProgram.setCode("MMM-1");
        loanProgramRespository.save(testLoanProgram);

        LoanEntity testLoan = new LoanEntity();
        testLoan.setLoanId(new LoanEntityPk(10,"AAA"));
        testLoan.setCode("LN-"+count.toString());
        testLoan.setName("Computer");
        testLoan.setAmount(new BigDecimal(123423));
        testLoan.setRate(new BigDecimal(4.375));
        loanRepository.save(testLoan);

        count = loanRepository.findAll().size();

        
        LoanEntity loan = loanRepository.findById(new LoanEntityPk(1,"AAA")).orElse(null);
        assertNotNull(loan);
        assertEquals("AAA",loan.loanId.getProgram());
        assertEquals(1,loan.loanId.getId());

        if (loan != null) {}
            loanRepository.delete(loan);
    }
 */
}
