package com.mrd.learn.repository;

import com.mrd.learn.entity.Answer;
import com.mrd.learn.entity.Quiz;
import com.mrd.learn.entity.dto.AnswerDTO;
import com.mrd.learn.entity.dto.QuizAnswerDTO;
import com.mrd.learn.entity.dto.QuizSearchForm;
import com.mrd.learn.utils.StringUtil;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ducnh
 */
@Repository
public class QuizRepositoryCustom extends BaseRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizRepositoryCustom.class);

    protected QuizRepositoryCustom(EntityManagerFactory factory) {
        super(factory);
    }

    public List<Quiz> getAll(QuizSearchForm data, int currentPage, int rowPerPage) {
        TypedQuery<Quiz> queryQuiz = this.buildSearchQueryQuiz(data, Quiz.class, false);
        super.initPaging(queryQuiz, currentPage, rowPerPage);
        return queryQuiz.getResultList();
    }

    public List<QuizAnswerDTO> search(QuizSearchForm data, int currentPage, int rowPerPage) {
        List<QuizAnswerDTO> quizAnswerDTOList = new ArrayList<>();
        List<AnswerDTO> answerList = null;
        List<Quiz> quizList = null;

        TypedQuery<Quiz> queryQuiz = this.buildSearchQueryQuiz(data, Quiz.class, false);
        super.initPaging(queryQuiz, currentPage, rowPerPage);
        quizList = queryQuiz.getResultList();
        if (quizList != null) {
            int i = 0;
            String[] idAnswerList = new String[quizList.size()];
            for (Quiz quiz : quizList) {
                idAnswerList[i] = quiz.getId().toString();
                i++;
            }
            answerList = SearchAnswerByQuizId(idAnswerList);

            if (answerList != null) {
                for (Quiz quiz : quizList) {
                    QuizAnswerDTO quizAnswerDTO = new QuizAnswerDTO();
                    List<AnswerDTO> lstAnsByQuizId = new ArrayList<>();
                    for (AnswerDTO ansDTO : answerList){
                        if(ansDTO.getQuizId() == quiz.getId()){
                            lstAnsByQuizId.add(ansDTO);
                        }
                    }
                    quizAnswerDTO.setQuiz(quiz);
                    quizAnswerDTO.setLstAnswer(lstAnsByQuizId);

                    quizAnswerDTOList.add(quizAnswerDTO);
                }

            } else {
                return null;
            }
        }
        return quizAnswerDTOList;
    }

    private <T> TypedQuery<T> buildSearchQueryQuiz(final QuizSearchForm data,
                                                   final Class<T> clazz, final boolean count) {
        String baseQuery = "Select q from Quiz q";
        StringBuilder sql = new StringBuilder(baseQuery);
        Map<String, Object> params = new HashMap<>();

        return super.createQuery(sql.toString(), params, clazz);

    }

    private List<AnswerDTO> SearchAnswerByQuizId(String[] idQuizlist) {
        Session session = openSession();
        try {
            String baseQuery = "Select a.id AS id, a.quiz_id AS quizId, a.content AS content, " +
                    " a.correct AS correct, a.description as description" +
                    " from answer a where a.quiz_id IN :idQuizlist";
            return session.createNativeQuery(baseQuery)
                    .setParameterList("idQuizlist", idQuizlist)
                    .addScalar("id", IntegerType.INSTANCE)
                    .addScalar("quizId", IntegerType.INSTANCE)
                    .addScalar("content", StringType.INSTANCE)
                    .addScalar("correct", BooleanType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(AnswerDTO.class))
                    .getResultList();
        } catch (Exception e) {
            LOGGER.error(e.toString());
        } finally {
            closeSession(session);
        }

        return null;

    }
}
