package cn.kxy.base.business;

public class EnvironmentData {
    private static String host;

    public static String getShareUrl() {
        String env = System.getProperty("env");
        String url = "";
        switch (env) {
            case "PRO":
                url = "https://laixuexi.coolcollege.cn";
                break;
            case "UAT":
                url = "https://knowledge-share.coolcollege.cn";
                break;
            case "T":
                url = "https://jcshareapi.coolcollege.cn";
                break;
            case "FAT9":
                url = "https://t9shareapi.coolcollege.cn";
                break;
            case "FAT6":
                url = "https://t6shareapi.coolcollege.cn";
                break;
            case "FAT5":
                url = "https://t5shareapi.coolcollege.cn";
                break;
            case "FAT4":
                url = "https://t4shareapi.coolcollege.cn";
                break;
            case "FAT3":
                url = "https://t3shareapi.coolcollege.cn";
                break;
            case "FAT2":
                url = "https://t2shareapi.coolcollege.cn";
                break;
            case "FAT1":
                url = "https://tshareapi.coolcollege.cn";
                break;
            case "DEV":
                url = "https://dshareapi.coolcollege.cn";
                break;
            default:
                url = "https://laixuexi.coolcollege.cn";
                break;
        }
        return url;
    }

    public static String getAPIGateway() {
        String env = System.getProperty("env");
        String url = "";
        switch (env) {
            case "PRO":
                url = "https://laixuexi.coolcollege.cn";
                break;
            case "T":
                url = "https://jclaixuexi.coolcollege.cn";
                break;
            case "UAT":
                url = "https://hdlaixuexi.coolcollege.cn";
                break;
            case "FAT9":
                url = "https://tapigateway.coolcollege.cn";
                break;
            default:
                url = "https://laixuexi.coolcollege.cn";
                break;
        }
        return url;
    }

    public static String getHost() {
        if(host == null) {
            String env = System.getProperty("env");
            switch (env) {
                case "GREEN":
                    host = "https://grcoolapi.coolcollege.cn";
                    break;
                case "PRO":
                    host = "https://coolapi.coolcollege.cn";
                    break;
                case "UAT":
                    host = "https://hdcoolapi.coolcollege.cn";
                    break;
                case "T":
                    host = "https://jccoolapi.coolcollege.cn";
                    break;
                case "FAT10":
                    host = "https://ct10coolapi.coolcollege.cn";
                    break;
                case "FAT9":
                    host = "https://ct9coolapi.coolcollege.cn";
                    break;
                case "FAT8":
                    host = "https://ct8oolapi.coolcollege.cn";
                    break;
                case "FAT7":
                    host = "https://ct7coolapi.coolcollege.cn";
                    break;
                case "FAT6":
                    host = "https://ct6coolapi.coolcollege.cn";
                    break;
                case "FAT5":
                    host = "https://ct5coolapi.coolcollege.cn";
                    break;
                case "FAT4":
                    host = "https://ct4coolapi.coolcollege.cn";
                    break;
                case "FAT3":
                    host = "https://ct3coolapi.coolcollege.cn";
                    break;
                case "FAT2":
                    host = "https://ct2coolapi.coolcollege.cn";
                    break;
                case "FAT1":
                    host = "https://ct1coolapi.coolcollege.cn";
                    break;
                case "DEV":
                    host = "https://dcoolapi.coolcollege.cn";
                    break;
                default:
                    host = "https://tcoolapi.coolcollege.cn";
                    break;
            }
        }
        return host;
    }
}
