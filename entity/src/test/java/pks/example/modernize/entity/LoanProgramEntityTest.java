package pks.example.modernize.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest
// @ActiveProfiles("h2db")
public class LoanProgramEntityTest {

    @Autowired
    private LoanProgramRespository loanProgramRespository;

    private LoanProgramEntity testLoanProgram;
    private Integer count = 0;

    @BeforeEach
    public void setUp() {
        testLoanProgram = new LoanProgramEntity();
        testLoanProgram.setCode("ASD-"+count.toString());
        testLoanProgram.setName("my big name");
        testLoanProgram.setDescription("this is a long description");
        loanProgramRespository.save(testLoanProgram);
    }

    @AfterEach
    public void tearDown() {
        loanProgramRespository.delete(testLoanProgram);
    }

    @Test
    void ExerciseLoanProgramRespositoryTest() {
        LoanProgramEntity loanProgram = loanProgramRespository.findById(testLoanProgram.getCode()).orElse(null);
        assertNotNull(loanProgram);
        assertEquals(loanProgram.getCode(), testLoanProgram.getCode());
        assertEquals(loanProgram.getName(), testLoanProgram.getName());
        assertEquals(loanProgram, testLoanProgram);
        assertEquals(4,loanProgramRespository.findAll().size());
    }

}
