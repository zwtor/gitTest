package cn.kxy.base.business;

import cn.kxy.examination.business.ExamTestQuestionsBusiness;
import cn.kxy.examination.business.ExaminationBusines;
import cn.kxy.examination.business.PaperBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import cn.kxy.lecturer.business.LecturerLevelBusiness;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

import com.alibaba.fastjson.JSONPath;

public class BaseBusiness {
   public static String token = TokenData.getMangerToken();
    public static String enterpriseId = EnterpriseData.getEnterpriseId();
    public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
    public static String platform_url = EnterpriseDataUrl.getPlatformUrl();
    public static String platform_login_url = platform_url + "login";
    public static String platform_logout_url = platform_url + "logout";
    public static String examName = "regularExam";
    public static String examPassName = "PassedPaperExam";
    public static String examPassNameByPractice = "practicePassExam";
    public static String certificate_name = "Pass_Exam_Certificate";
    public static String paper_name = "SettledPaper";
    public static String pass_paper_name = "PassedPaper";
    public static String queryListUrl = enterpriseUrl + "course/question/getList";
    public static String questionnaireName = "SettleQuestionnaire";
    public static String lecturerLevel = "SeniorLecturer";
    public static String outlecturerLevel = "outSideSeniorLecturer";

    public static String deleteQuoteDataUrl() {
        return enterpriseUrl + "v2/" + enterpriseId + "/users/" + UserBusiness.getUserId() + "/delete/check";
    }
    
    /**
     * @throws
     * @Title: deleteQuoteData
     * @Description: TODO(删除前对引用的数据进行校验 ， 是否可以删除)
     * @param: @param id
     * @param: @param type： questionnaire--问卷，
     * @param: @return
     * @return: String
     */
    public static String deleteCheckData(String id, String type) {
        return GetRequestTools.RequestQueryParamsByGet("id", id, "type", type, "page_number", "1", "page_size", "10",
                "access_token", token, deleteQuoteDataUrl());
    }

    /**
     * @throws
     * @Title: addLecturerLevel
     * @Description: TODO(初始化讲师等级)
     * @param:
     * @return: void
     */
    public static void addLecturerLevel() {
        String standard = "Lecture length must be greater than 100 hours";
        String res = LecturerLevelBusiness.queryLecturerLevelList(lecturerLevel);
        int total = (int) JSONPath.read(res, "$.total");
        int le_total = Integer.valueOf(total);
        if (le_total == 0) {
            LecturerLevelBusiness.addLecturerLevel(lecturerLevel, standard);
        }
    }

    public static void addOutLecturerLevel() {
        String standard = "Lecture length must be greater than 100 hours";
        String res = LecturerLevelBusiness.queryLecturerLevelList(outlecturerLevel);
        int total = (int) JSONPath.read(res, "$.total");
        int le_total = Integer.valueOf(total);
        if (le_total == 0) {
            LecturerLevelBusiness.addOutsideLecturerLevel(outlecturerLevel, standard);
        }
    }

    /**
     * @throws
     * @Title: addQuestionnaire
     * @Description: TODO(初始化问卷)
     * @param:
     * @return: void
     */
    public static void addQuestionnaire() {
        String res = QuestionnaireBusiness.queryLsit(questionnaireName, "all", "", "");
        int total = (int) JSONPath.read(res, "$.questionnaires.total");
        if (total == 0) {
            QuestionnaireBusiness.addQuestionnaire(questionnaireName, "true", "true", "true", "true", "true");
        }
    }

    // -----------------------------------------------------------------------初始化试卷----------------------------------------------------------------------


    public static void addPassPaper() {
        String res01 = PaperBusiness.queryList(pass_paper_name, "false");
        int total = (int) JSONPath.read(res01, "$.total");

        String totalScore = "100";
        String passingScore = "60";
        String firstScore = "40";
        String SecondScore = "60";
        if (total == 0) {

            String res02 = ExaminationBusines.queryQuestionBankList(examPassName, "false", "");
            String id = (String) JSONPath.read(res02, "$.data.list[0].id");
            String res03 = GetRequestTools.RequestQueryParamsByGet("keyword", "", "onlySeeMe", "false",
                    "questionBankId", id, "type", "", "pageSize", "20", "pageNumber", "1", "access_token",
                    token, queryListUrl);
            String question_id01 = (String) JSONPath.read(res03, "$.list[0].id");
            String question_id02 = (String) JSONPath.read(res03, "$.list[1].id");

            PostRequestTools.RequestFormDataByPost(token, "title",
                    pass_paper_name, "summary", "this is a summary", "totalScore",
                    totalScore, "passingScore", "60", "passLine", passingScore, "courseClassify",
                    ClassificationBusines.getPrimaryId(), "questionVoInfo",
                    "[{\"questionId\":\"" + question_id01 + "\",\"score\":" + firstScore + "},{\"questionId\":\""
                            + question_id02 + "\",\"score\":" + SecondScore + "}]",
                    PaperBusiness.addUrl);


        }
    }

    public static void addPaper() {
        String res01 = PaperBusiness.queryList(paper_name, "false");
        int total = (int) JSONPath.read(res01, "$.total");

        String totalScore = "100";
        String passingScore = "60";
        String firstScore = "10";
        String SecondScore = "20";
        String ThirdScore = "20";
        String fourthScore = "20";
        String fivethScore = "30";
        if (total == 0) {
            String res02 = ExaminationBusines.queryQuestionBankList(examName, "false", "");
            String id = (String) JSONPath.read(res02, "$.data.list[0].id");
            String res03 = GetRequestTools.RequestQueryParamsByGet("keyword", "", "onlySeeMe", "false",
                    "questionBankId", id, "type", "", "pageSize", "20", "pageNumber", "1", "access_token",
                    token, queryListUrl);
            String question_id01 = (String) JSONPath.read(res03, "$.list[0].id");
            String question_id02 = (String) JSONPath.read(res03, "$.list[1].id");
            String question_id03 = (String) JSONPath.read(res03, "$.list[2].id");
            String question_id04 = (String) JSONPath.read(res03, "$.list[3].id");
            String question_id05 = (String) JSONPath.read(res03, "$.list[4].id");

            PostRequestTools.RequestFormDataByPost(token, "title",
                    paper_name, "summary", "this is a summary", "totalScore",
                    totalScore, "passingScore", "60", "passLine", passingScore, "courseClassify",
                    ClassificationBusines.getPrimaryId(), "questionVoInfo",
                    "[{\"questionId\":\"" + question_id01 + "\",\"score\":" + firstScore + "},{\"questionId\":\""
                            + question_id02 + "\",\"score\":" + SecondScore + "}" + ",{\"questionId\":\""
                            + question_id03 + "\",\"score\":" + ThirdScore + "},,{\"questionId\":\"" + question_id04 + "\",\"score\":" + fourthScore + "}"
                            + ",{\"questionId\":\"" + question_id05 + "\",\"score\":" + fivethScore + "}]",
                    PaperBusiness.addUrl);
        }
    }

    //--------------------------------------------------------初始化题库练习使用的试题------------------------------------------------------------------
    public static void addPassExamToPractice() {
        String res = ExaminationBusines.queryQuestionBankList(examPassNameByPractice, "false", "");

        int total = (int) JSONPath.read(res, "$.data.total");


        if (total == 0) {
            ExaminationBusines.createQuestionBank(examPassNameByPractice, "1", "", "");
        }
        String res01 = ExaminationBusines.queryQuestionBankList(examPassNameByPractice, "false", "");
        int totalCount = (int) JSONPath.read(res01, "$.data.list[0].totalCount");

        String id = (String) JSONPath.read(res01, "$.data.list[0].id");
        if (totalCount == 0) {
            ExamTestQuestionsBusiness.addQuestions("sinagleChoice", id, "1", "1", "sinagleChoice Answer analysis", "",
                    "", "",
                    "[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"id\":\"\"},{\"title\":\"three\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"four\",\"id\":\"\"}]");
            ExamTestQuestionsBusiness.addQuestions("sinagleChoiceTwo", id, "1", "1", "sinagleChoice Answer analysis", "",
                    "", "",
                    "[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"id\":\"\"},{\"title\":\"three\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"four\",\"id\":\"\"}]");
        }
    }

    // -----------------------------------------------------------------------初始化试题----------------------------------------------------------------------

    public static void addPassExam() {
        String res = ExaminationBusines.queryQuestionBankList(examPassName, "false", "");

        int total = (int) JSONPath.read(res, "$.data.total");


        if (total == 0) {
            ExaminationBusines.createQuestionBank(examPassName, "1", "", "");
        }
        String res01 = ExaminationBusines.queryQuestionBankList(examPassName, "false", "");
        int totalCount = (int) JSONPath.read(res01, "$.data.list[0].totalCount");

        String id = (String) JSONPath.read(res01, "$.data.list[0].id");
        if (totalCount == 0) {
            ExamTestQuestionsBusiness.addQuestions("sinagleChoice", id, "1", "1", "sinagleChoice Answer analysis", "",
                    "", "",
                    "[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"id\":\"\"},{\"title\":\"three\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"four\",\"id\":\"\"}]");
            ExamTestQuestionsBusiness.addQuestions("1+1=[[2]],2+2=[[4]]", id, "4", "4",
                    "JudgementQuestion Answer analysis", "", "", "",
                    "[{\"title\":\"2\",\"isAnswer\":true},{\"title\":\"4\",\"isAnswer\":true}]");
        }
    }

    public static void addExam() {

        String res = ExaminationBusines.queryQuestionBankList(examName, "false", "");

        int total = (int) JSONPath.read(res, "$.data.total");


        if (total == 0) {
            ExaminationBusines.createQuestionBank(examName, "1", "", "");
        }
        String res01 = ExaminationBusines.queryQuestionBankList(examName, "false", "");
        int totalCount = (int) JSONPath.read(res01, "$.data.list[0].totalCount");

        String id = (String) JSONPath.read(res01, "$.data.list[0].id");
        if (totalCount == 0) {
            ExamTestQuestionsBusiness.addQuestions("sinagleChoice", id, "1", "1", "sinagleChoice Answer analysis", "",
                    "", "",
                    "[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"id\":\"\"},{\"title\":\"three\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"four\",\"id\":\"\"}]");

            ExamTestQuestionsBusiness.addQuestions("Multiplechoice", id, "2", "2", "Multiplechoice Answer analysis", "",
                    "", "",
                    "[{\"title\":\"01\",\"id\":\"\"},{\"title\":\"02\",\"id\":\"\"},{\"title\":\"03\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"04\",\"isAnswer\":true,\"id\":\"\"}]");

            ExamTestQuestionsBusiness.addQuestions("JudgementQuestion", id, "3", "3",
                    "JudgementQuestion Answer analysis", "", "", "",
                    "[{\"title\":\"对\",\"id\":\"\"},{\"title\":\"错\",\"isAnswer\":true,\"id\":\"\"}]");
            ExamTestQuestionsBusiness.addQuestions("1+1=[[2]],2+2=[[4]]", id, "4", "4",
                    "JudgementQuestion Answer analysis", "", "", "",
                    "[{\"title\":\"2\",\"isAnswer\":true},{\"title\":\"4\",\"isAnswer\":true}]");
            ExamTestQuestionsBusiness.addQuestions("Short Answer Questions", id, "5", "1",
                    "Short Answer Questions Answer analysis", "", "", "",
                    "[{\"title\":\"true\",\"isAnswer\":true,\"id\":\"\"},{\"title\":\"false\",\"isAnswer\":true,\"id\":\"\"}]");

        }

    }

}
