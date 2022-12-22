public class Main {
    HanLPClient client = new HanLPClient("https://hanlp.hankcs.com/api", null); // Replace null with your auth
System.out.println(client.parse("2021年HanLPv2.1为生产环境带来次世代最先进的多语种NLP技术。晓美焰来到北京立方庭参观自然语义科技公司。"));
}
