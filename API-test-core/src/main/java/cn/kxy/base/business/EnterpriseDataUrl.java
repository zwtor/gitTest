package cn.kxy.base.business;

public class EnterpriseDataUrl {

    public static String getFeedUrl() {
        return getDomainName() + "feedapi/";
    }

    public static String getOfflineCourseUrl() {
        return getDomainName() + "offline-course/";
    }

    public static String getResourcemanagerUrl() {
        return getDomainName() + "cool-resource-manager/";
    }

    public static String getGzsubsidyUrl() {
        return getDomainName() + "gz-subsidy/";
    }

    public static String getEvaluationUrl() {
        return getDomainName() + "evaluation-api/";
    }

    public static String getGroupUrl() {
        return getDomainName() + "cool-group/";
    }

    public static String getMessageUrl() {
        return getDomainName() + "message-api/";
    }

    public static String getLiveUrl() {
        return getDomainName() + "live-api/";
    }

    public static String getAuthorityUrl() {
        return getDomainName() + "authority-service/";
    }

    public static String getEnterpriseUrl() {
        return getDomainName() + "enterprise-api/";
    }

    public static String getCmsUrl() {
        return getDomainName() + "cms-manager/";
    }

    public static String getCmdbUrl() {
        return getDomainName() + "cmdb-api/";
    }

    public static String getPlatformUrl() {
        return getDomainName() + "platform-api";
    }

    public static String getInteractionUrl() {
        return getDomainName() + "interaction-api/";
    }

    public static String getTutorsUrl() {
        return getDomainName() + "tutor-manager/";
    }

    public static String getTalentAbilityUrl() {
        return getDomainName() + "talentability/";
    }
    public static String getDictionaryServiceUrl() {
        return getDomainName() + "dictionary-service/";
    }

    public static String getShareUrl() {
        String env = System.getProperty("env");
        String url = "";
        switch (env) {
            case "PRO":
                url = "https://laixuexi.coolcollege.cn/";
                break;
            case "UAT":
                url = "https://knowledge-share.coolcollege.cn/";
                break;
            case "T":
                url = "https://jcshareapi.coolcollege.cn/";
                break;
            case "FAT9":
                url = "https://t9shareapi.coolcollege.cn/";
                break;
            case "FAT6":
                url = "https://t6shareapi.coolcollege.cn/";
                break;
            case "FAT5":
                url = "https://t5shareapi.coolcollege.cn/";
                break;
            case "FAT4":
                url = "https://t4shareapi.coolcollege.cn/";
                break;
            case "FAT3":
                url = "https://t3shareapi.coolcollege.cn/";
                break;
            case "FAT2":
                url = "https://t2shareapi.coolcollege.cn/";
                break;
            case "FAT1":
                url = "https://tshareapi.coolcollege.cn/";
                break;
            case "DEV":
                url = "https://dshareapi.coolcollege.cn/";
                break;
            default:
                url = "https://laixuexi.coolcollege.cn/";
                break;
        }
        return url;
    }

    public static String getApigateway() {
        String env = System.getProperty("env");
        String url = "";
        switch (env) {
            case "PRO":
                url = "https://laixuexi.coolcollege.cn/";
                break;
            case "T":
                url = "https://jclaixuexi.coolcollege.cn/";
                break;
            case "UAT":
                url = "https://hdlaixuexi.coolcollege.cn/";
                break;
            case "FAT9":
                url = "https://tapigateway.coolcollege.cn/";
                break;
            default:
                url = "https://laixuexi.coolcollege.cn/";
                break;
        }
        return url;
    }

    public static String getDomainName() {
        String env = System.getProperty("env");
        String url = "";
        switch (env) {
            case "GREEN":
                url = "https://grcoolapi.coolcollege.cn/";
                break;
            case "PRO":
                url = "https://coolapi.coolcollege.cn/";
                break;
            case "UAT":
                url = "https://hdcoolapi.coolcollege.cn/";
                break;
            case "T":
                url = "https://jccoolapi.coolcollege.cn/";
                break;
            case "FAT10":
                url = "https://ct10coolapi.coolcollege.cn/";
                break;
            case "FAT9":
                url = "https://ct9coolapi.coolcollege.cn/";
                break;
            case "FAT8":
                url = "https://ct8oolapi.coolcollege.cn/";
                break;
            case "FAT7":
                url = "https://ct7coolapi.coolcollege.cn/";
                break;
            case "FAT6":
                url = "https://ct6coolapi.coolcollege.cn/";
                break;
            case "FAT5":
                url = "https://ct5coolapi.coolcollege.cn/";
                break;
            case "FAT4":
                url = "https://ct4coolapi.coolcollege.cn/";
                break;
            case "FAT3":
                url = "https://ct3coolapi.coolcollege.cn/";
                break;
            case "FAT2":
                url = "https://ct2coolapi.coolcollege.cn/";
                break;
            case "FAT1":
                url = "https://ct1coolapi.coolcollege.cn/";
                break;
            case "DEV":
                url = "https://dcoolapi.coolcollege.cn/";
                break;
            default:
                url = "https://tcoolapi.coolcollege.cn/";
                break;
        }
        return url;
    }
}
