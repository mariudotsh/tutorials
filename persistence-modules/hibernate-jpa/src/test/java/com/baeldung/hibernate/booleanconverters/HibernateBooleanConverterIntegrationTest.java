package com.baeldung.hibernate.booleanconverters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.baeldung.hibernate.HibernateUtil;
import com.baeldung.hibernate.booleanconverters.model.Question;
import com.baeldung.hibernate.booleanconverters.model.QuestionBuilder;

public class HibernateBooleanConverterIntegrationTest {

    private static final String PROPERTY_FILE_NAME = "hibernate-booleanconverters.properties";

    private static SessionFactory sessionFactory;

    private static Session session;

    @BeforeAll
    public static void beforeTests() throws IOException {
        sessionFactory = HibernateUtil.getSessionFactory(PROPERTY_FILE_NAME);
        session = sessionFactory.openSession();
    }

    @Test
    void when_Y_Or_N_ThenConversionToDomainModelWorksBothWays() {
        session.beginTransaction();
        session.createNativeMutationQuery("INSERT INTO Question (id, content, correctAnswer) VALUES\n"
          + "(1, 'Should I learn programming?', 'Y'),\n"
          + "(2, 'Question two', 'N')").executeUpdate();
        Question questionThree = new QuestionBuilder().id(3L).content("Are penguins birds?").correctAnswer(true).build();
        Question questionFour = new QuestionBuilder().id(4L).content("").correctAnswer(false).build();
        session.persist(questionThree);
        session.persist(questionFour);
        session.flush();

        Question shouldILearnProgramming = session.get(Question.class, 1);
        Question questionTwo= session.get(Question.class, 2);
        Character questionThreeCorrectAnswer = session.createNativeQuery("SELECT correctAnswer FROM Question WHERE id=3", Character.class).getSingleResult();
        Character questionFourCorrectAnswer = session.createNativeQuery("SELECT correctAnswer FROM Question WHERE id=4", Character.class).getSingleResult();

        assertTrue(shouldILearnProgramming.getCorrectAnswer());
        assertFalse(questionTwo.getCorrectAnswer());
        assertEquals('Y', questionThreeCorrectAnswer);
        assertEquals('N', questionFourCorrectAnswer);
    }

    @Test
    void when_T_Or_F_ThenConversionToDomainModelWorksBothWays() {
        session.beginTransaction();

        session.createNativeMutationQuery("INSERT INTO Question (id, content, shouldBeAsked) VALUES\n"
          + "(5, 'Is this code tested?', 'T'),\n"
          + "(6, 'How much money do you make', 'F')").executeUpdate();
        Question questionThree = new QuestionBuilder().id(7L).content("").shouldBeAsked(true).build();
        Question questionFour = new QuestionBuilder().id(8L).content("").shouldBeAsked(false).build();
        session.persist(questionThree);
        session.persist(questionFour);
        session.flush();
        Question shouldILearnProgramming = session.get(Question.class, 5);
        Question questionTwo= session.get(Question.class, 6);
        Character questionThreeCorrectAnswer = session.createNativeQuery("SELECT shouldBeAsked FROM Question WHERE id=7", Character.class).getSingleResult();
        Character questionFourCorrectAnswer = session.createNativeQuery("SELECT shouldBeAsked FROM Question WHERE id=8", Character.class).getSingleResult();

        assertTrue(shouldILearnProgramming.getShouldBeAsked());
        assertFalse(questionTwo.getShouldBeAsked());
        assertEquals('T', questionThreeCorrectAnswer);
        assertEquals('F', questionFourCorrectAnswer);
    }

    @Test
    void when_1_Or_0_ThenConversionToDomainModelWorksBothWays() {
        session.beginTransaction();

        session.createNativeMutationQuery("INSERT INTO Question (id, content, isEasy) VALUES\n"
          + "(9, 'Do you like Java?', 1),\n"
          + "(10, 'How much money do you make', 0)").executeUpdate();
        Question questionThree = new QuestionBuilder().id(11L).content("Are penguins birds?").isEasy(true).build();
        Question questionFour = new QuestionBuilder().id(12L).content("").isEasy(false).build();
        session.persist(questionThree);
        session.persist(questionFour);
        session.flush();
        Question shouldILearnProgramming = session.get(Question.class, 9);
        Question questionTwo= session.get(Question.class, 10);
        Integer questionThreeCorrectAnswer = session.createNativeQuery("SELECT isEasy FROM Question WHERE id=11", Integer.class).getSingleResult();
        Integer questionFourCorrectAnswer = session.createNativeQuery("SELECT isEasy FROM Question WHERE id=12", Integer.class).getSingleResult();

        assertTrue(shouldILearnProgramming.getEasy());
        assertFalse(questionTwo.getEasy());
        assertEquals(1, questionThreeCorrectAnswer);
        assertEquals(0, questionFourCorrectAnswer);
    }

    @Test
    void whenLowercase_y_or_n_ThenDomainModelValueNull() {
        session.beginTransaction();

        session.createNativeMutationQuery("INSERT INTO Question (id, content, correctAnswer) VALUES\n"
          + "(13, 'Something', 'y')").executeUpdate();
        Question shouldILearnProgramming = session.get(Question.class, 13);

        assertNull(shouldILearnProgramming.getCorrectAnswer());
    }

    @Test
    void givenConverterRegisteredToAutoApply_whenFieldIsNotAnnotated_ThenConversionToDomainModelWorksBothWays() {
        session.beginTransaction();
        session.createNativeMutationQuery("INSERT INTO Question (id, content, converterAutoApplies) VALUES\n"
          + "(14, 'Should I learn programming?', 'Y'),\n"
          + "(15, 'Question two', 'N')").executeUpdate();
        Question questionThree = new QuestionBuilder().id(14L).content("Are penguins birds?").converterAutoApplies(true).build();
        Question questionFour = new QuestionBuilder().id(15L).content("").converterAutoApplies(false).build();
        session.persist(questionThree);
        session.persist(questionFour);
        session.flush();

        Question shouldILearnProgramming = session.get(Question.class, 14);
        Question questionTwo= session.get(Question.class, 15);
        Character questionThreeCorrectAnswer = session.createNativeQuery("SELECT converterAutoApplies FROM Question WHERE id=16", Character.class).getSingleResult();
        Character questionFourCorrectAnswer = session.createNativeQuery("SELECT converterAutoApplies FROM Question WHERE id=17", Character.class).getSingleResult();

        assertTrue(shouldILearnProgramming.getConverterAutoApplies());
        assertFalse(questionTwo.getConverterAutoApplies());
        assertEquals('Y', questionThreeCorrectAnswer);
        assertEquals('N', questionFourCorrectAnswer);
    }

    @AfterAll
    static void afterTests() {
        sessionFactory.close();
    }
}
