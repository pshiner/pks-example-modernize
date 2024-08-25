package pks.example.modernize.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;


// @DataJdbcTest(properties = {
//     "spring.datasource.url=jdbc:h2:mem:testdb",
//     "spring.datasource.username=test",
//     "spring.datasource.password=",
//     "spring.jpa.hibernate.ddl-auto=create-drop"
// })
// @RunWith
@SpringBootTest
public class LoanProgramEntityTest {

    @Autowired
    private LoanProgramRespository loanProgramRespository;

    private LoanProgram testLoanProgram;
    private Integer count = 0;

    @BeforeEach
    public void setUp() {
        testLoanProgram = new LoanProgram();
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
        LoanProgram loanProgram = loanProgramRespository.findById(testLoanProgram.getCode()).orElse(null);
        assertNotNull(loanProgram);
        assertEquals(loanProgram.getCode(), testLoanProgram.getCode());
        assertEquals(loanProgram.getName(), testLoanProgram.getName());
        assertEquals(loanProgram, testLoanProgram);
    }

}
