package com.kps.sb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static String extract(String s,String lb, String rb){
        String res=s.substring(s.indexOf(lb) + lb.length(), s.indexOf(rb));
        return res;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

/////////////EMULATING
    @GetMapping(value = "/CreditRegistry_SPARK", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_05_50_Spark(HttpServletRequest request) {
        String rq = request.toString();
        String soapAction=extract(rq,"soapAction=\"/","\">");
        //для DS_05_50_Spark
        if(soapAction.equals("CRDS")){
        String InquiryCode =extract(rq,"<InquiryCode>","</InquiryCode>");
        String ProcessCode = extract(rq,"<ProcessCode>","</ProcessCode>");
        String LayoutVersion = extract(rq,"<LayoutVersion>","</LayoutVersion>");
        String IN_CUSTOMER_BASICSALARY=extract(rq,"IN_CUSTOMER_BASICSALARY",".00");
        String IN_APPL_REQUEST_DATE=extract(rq,"<IN_APPL_REQUEST_DATE>","</IN_APPL_REQUEST_DATE>");//Current YYYY-MM-DD
        String IN_APPL_DEC_VAR_VALUE=extract(rq,"<IN_APPL_DEC_VAR_VALUE>","</IN_APPL_DEC_VAR_VALUE>");//Current DD-M-YYYY
        String IN_CUSTOMER_ID=extract(rq,"<IN_CUSTOMER_ID>","<IN_CUSTOMER_ID>");
        Locale locale=Locale.US;
        DateFormat dateFormat = new SimpleDateFormat("E MMM d yyyy HH:mm:ss 'GMT'Z (z)",locale);
        Date date= new Date();
        String LONG_DATA = dateFormat.format(date);
        String ProcessVersion=extract(rq,"<ProcessVersion>","</ProcessVersion>");


        String main_response =("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                      "<StrategyOneResponseT5>\n" +
                           "<Header>\n" +
                               "<InquiryCode>"+InquiryCode+"</InquiryCode>\n" +
                               "<ProcessCode>"+ProcessCode+"</ProcessCode>\n" +
                               "<OrganizationCode>CRDS</OrganizationCode>\n" +
                               "<ProcessVersion>"+ProcessVersion+"</ProcessVersion>\n" +
                               "<LayoutVersion>"+LayoutVersion+"</LayoutVersion>\n" +
                           "</Header>\n" +
                           "<Body>\n" +
                               "<Application>\n" +
                                   "<Variables>\n" +
                                       "<OUT_APPL_APPLICATIONID>"+InquiryCode+"</OUT_APPL_APPLICATIONID>\n" +
                                       "<OUT_APPL_REQUEST_TYPE>BASE</OUT_APPL_REQUEST_TYPE>\n" +
                                       "<OUT_APPL_DEC_RESULT>PREAPPROVE</OUT_APPL_DEC_RESULT>\n" +
                                       "<OUT_APPL_DEC_TIMEOUT>0</OUT_APPL_DEC_TIMEOUT>\n" +
                                       "<OUT_APPL_DEC_PD>0.06535847331527</OUT_APPL_DEC_PD>\n" +
                                       "<OUT_APPL_DEC_SCORE>241</OUT_APPL_DEC_SCORE>\n" +
                                       "<OUT_APPL_PTI>0.0768</OUT_APPL_PTI>\n" +
                                   "</Variables>\n" +
                                   "<Categories>\n" +
                                       "<CUSTOMER>\n" +
                                           "<Variables>\n" +
                                               "<OUT_CUSTOMER_ID>"+IN_CUSTOMER_ID+"</OUT_CUSTOMER_ID>\n" +
                                               "<OUT_CUSTOMER_PTI>0.0768</OUT_CUSTOMER_PTI>\n" +
                                               "<OUT_CUSTOMER_DEC_RESULT>ACCEPT</OUT_CUSTOMER_DEC_RESULT>\n" +
                                               "<OUT_CUSTOMER_DEC_PD>0.06535847331527</OUT_CUSTOMER_DEC_PD>\n" +
                                               "<OUT_CUSTOMER_DEC_SCORE>241</OUT_CUSTOMER_DEC_SCORE>\n" +
                                               "<OUT_CUSTOMER_DEC_CATEGORY>S</OUT_CUSTOMER_DEC_CATEGORY>\n" +
                                               "<OUT_CUSTOMER_DEC_CATEGORY_EXT>S</OUT_CUSTOMER_DEC_CATEGORY_EXT>\n" +
                                               "<OUT_CUSTOMER_DEC_CATEGORY_PC>S</OUT_CUSTOMER_DEC_CATEGORY_PC>\n" +
                                               "<OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>КИ отсутствует</OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>\n" +
                                               "<OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>0</OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>\n" +
                                               "<OUT_CUSTOMER_TOTALINCOME>"+IN_CUSTOMER_BASICSALARY+"</OUT_CUSTOMER_TOTALINCOME>\n" +
                                           "</Variables>\n" +
                                           "<Categories>\n" +
                                               "<OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_DEC_VERIFICATION_PART>CM_DOC_MAIN</OUT_CUSTOMER_DEC_VERIFICATION_PART>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                                               "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_CODE>AppValue</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_TYPE>SI</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_NAME>Значение суммарного дохода участника сделки</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>"+IN_CUSTOMER_BASICSALARY+".00</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_RULES>\n" +
                                               "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_CODE>S008_fsr</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_NAME>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_RULES>\n" +
                                               "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_CODE>CM005</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_NAME>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_RULES>\n" +
                                               "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_CODE>CM006</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_NAME>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_RULES>\n" +
                                               "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_CODE>CM011</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_NAME> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                       "<OUT_CUSTOMER_DEC_RS_DESCRIPTION> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_RULES>\n" +
                                               "<OUT_CUSTOMER_DEC_SCORES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_DEC_SCORES_SCORE>-5.33858937638346</OUT_CUSTOMER_DEC_SCORES_SCORE>\n" +
                                                       "<OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>241</OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>\n" +
                                                       "<OUT_CUSTOMER_DEC_SCORES_PD>0.06535847331527</OUT_CUSTOMER_DEC_SCORES_PD>\n" +
                                                       "<OUT_CUSTOMER_DEC_SCORES_CUTOFF>0.421</OUT_CUSTOMER_DEC_SCORES_CUTOFF>\n" +
                                                       "<OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>110</OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>\n" +
                                                   "</Variables>\n" +
                                                   "<Categories />\n" +
                                               "</OUT_CUSTOMER_DEC_SCORES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>1</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_CFC00_CFD01_CFP02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>480000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>2</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY12MONTH_TO_INQUIRY6MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>1.66666666666666</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>3</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Max_delay_PFP01_max_cfa00_cfm02_CFN00_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>-1000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>4</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNT_cfa01_CFO02_CFT07</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>5</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_client_type</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>3</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>6</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_segment</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>4</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>7</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa01_CFT12</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>1000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>8</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY3MONTH_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>9</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_first_opened_CFM02_CFN00</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>126</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>10</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD03_PFP01_sum_CFM00_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>11</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD01_PFP03_sum_CFM01_CFN00</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>12</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_cfa00_cfd00_CFT09_CFU01</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>1000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>1</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY12MONTH_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>15</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>2</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD01_PFP00_sum_CFM00_CFN00_TO_CNTp_PFD00_PFP00_sum_CFM00</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>3</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY1WEEK_TO_INQUIRY1WEEK_avg_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>4</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD02_PFP03_sum_CFM00_CFN00_TO_CNTp_PFD00_PFP03_sum_CFM00</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>10000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>5</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_first_opened_CFM01_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>126</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>6</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY3MONTH_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>7</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa01_CFT08</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0.4006</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>8</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNTp_PFD00_PFP00_sum_CFM00_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>148</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>9</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY6MONTH_TO_INQUIRY9MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>1</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>10</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNT_CFC00_CFD01_CFP01</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>8</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>11</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_CFM00_CFN02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>36</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>12</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY3MONTH_TO_INQUIRY3MONTH_avg_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0.0177162527751402</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>13</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT04</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>14</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_cfa00_CFO01_CFT05</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>15</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT03</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0.04497224820704709</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>16</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT09</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>17</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_cfa01_cfc02_CFD01_cft03</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>180000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>18</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_CFO01_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>19</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_CNT_cfa00_CFO03_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>20</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_INQUIRY1WEEK_TO_INQUIRY1MONTH_RATIO</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>21</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_cfa01_CFO02_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>22</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_first_opened_cfa00_CFT11_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>-10000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>23</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_cfa02_cfd00_CFT08_CFU00</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>43</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>24</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_CFM01_CFN00</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>36</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>25</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_CFM03_CFN01</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>46</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>26</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Max_Limit_CFC03_CFD01_CFP00</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>27</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_Months_since_last_opened_cfa01_cfd01_CFT00_CFU02</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>-10000</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUS_DEC_VAR_NAME>28</OUT_CUS_DEC_VAR_NAME>\n" +
                                                       "<OUT_CUS_DEC_VAR_DESCR>v_OUTS_to_LIMIT_wavg_cfa00_CFT08</OUT_CUS_DEC_VAR_DESCR>\n" +
                                                       "<OUT_CUS_DEC_VAR_TYPE>FICHI:</OUT_CUS_DEC_VAR_TYPE>\n" +
                                                       "<OUT_CUS_DEC_VAR_VALUE>0.0285</OUT_CUS_DEC_VAR_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_CUSTOMER_DEC_VARIABLES>\n" +
                                               "<OUT_CUSTOMER_SERVICES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_SERVICES_NAME>BIAS_FSSP</OUT_CUSTOMER_SERVICES_NAME>\n" +
                                                       "<OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                                                   "</Variables>\n" +
                                                   "<Categories />\n" +
                                               "</OUT_CUSTOMER_SERVICES>\n" +
                                               "<OUT_CUSTOMER_SERVICES>\n" +
                                                   "<Variables>\n" +
                                                       "<OUT_CUSTOMER_SERVICES_NAME>BIAS_NEGATIV</OUT_CUSTOMER_SERVICES_NAME>\n" +
                                                       "<OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                                                   "</Variables>\n" +
                                                   "<Categories />\n" +
                                               "</OUT_CUSTOMER_SERVICES>\n" +
                                               "<OUT_CUSTOMER_PDN>\n" +
                                                   "<Variables />\n" +
                                                   "<Categories>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>cusType</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>Applicant</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn1</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn0</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplRel5</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnDeclaredIncome</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnRetailIncome</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnModelingIncome</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                           "<Variables>\n" +
                                                               "<COMMON_PROPERTY_NAME>pdnIncomeCalcPeriod</COMMON_PROPERTY_NAME>\n" +
                                                               "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                               "<COMMON_PROPERTY_VALUE>12</COMMON_PROPERTY_VALUE>\n" +
                                                           "</Variables>\n" +
                                                       "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>109dfa6e-cea5-11eb-a8dd-786d75af10c6-6</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>00</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>109dfa6e-cea5-11eb-a8dd-786d75af10c6-6</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>ebb81198-244b-11eb-a191-772fa2def8cb-4</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>ebb81198-244b-11eb-a191-772fa2def8cb-4</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>8dfc47fa-5fad-11ea-95cb-f9c132249232-5</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>8dfc47fa-5fad-11ea-95cb-f9c132249232-5</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>ad93b240-dd3f-11ea-993d-95f185b924a2-6</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>ad93b240-dd3f-11ea-993d-95f185b924a2-6</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f30e86ef-dd3e-11ea-8c25-bd118258a476-6</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>f30e86ef-dd3e-11ea-8c25-bd118258a476-6</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f26ed548-eacc-11ea-9e86-762de70bade2-d</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>f26ed548-eacc-11ea-9e86-762de70bade2-d</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f26f4cb2-eacc-11ea-9e86-682ad6d5c89b-3</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>f26f4cb2-eacc-11ea-9e86-682ad6d5c89b-3</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                       "<OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                           "<Variables>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_UUID>f0787ece-3a66-185a-be59-7feee209487a-c</OUT_CUSTOMER_PDN_DEC_LOAN_UUID>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>26115248</OUT_CUSTOMER_PDN_DEC_LOAN_GROUPID>\n" +
                                                           "</Variables>\n" +
                                                           "<Categories>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>loanStatus</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>13</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>"+IN_CUSTOMER_ID+"</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>GROUPID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>26115248</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>UUID</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>f0787ece-3a66-185a-be59-7feee209487a-c</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                               "<OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                                   "<Variables>\n" +
                                                                       "<COMMON_PROPERTY_NAME>pdnInvolvedType</COMMON_PROPERTY_NAME>\n" +
                                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                                   "</Variables>\n" +
                                                               "</OUT_CUSTOMER_PDN_DEC_LOAN_COMMON_PROPERTY>\n" +
                                                           "</Categories>\n" +
                                                       "</OUT_CUSTOMER_PDN_DEC_LOAN>\n" +
                                                   "</Categories>\n" +
                                               "</OUT_CUSTOMER_PDN>\n" +
                                           "</Categories>\n" +
                                       "</CUSTOMER>\n" +
                                       "<OUT_APPL_DEC_OFFERS>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_OFFERS_ID>1</OUT_APPL_DEC_OFFERS_ID>\n" +
                                               "<OUT_APPL_DEC_OFFERS_CREDITLIMIT>100000</OUT_APPL_DEC_OFFERS_CREDITLIMIT>\n" +
                                               "<OUT_APPL_DEC_OFFERS_MINLIMIT>9999</OUT_APPL_DEC_OFFERS_MINLIMIT>\n" +
                                               "<OUT_APPL_DEC_OFFERS_APR>0.209</OUT_APPL_DEC_OFFERS_APR>\n" +
                                               "<OUT_APPL_DEC_OFFERS_PRODUCTID>110</OUT_APPL_DEC_OFFERS_PRODUCTID>\n" +
                                               "<OUT_APPL_DEC_OFFERS_MAIN>1</OUT_APPL_DEC_OFFERS_MAIN>\n" +
                                               "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>100000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>\n" +
                                               "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>100000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>\n" +
                                               "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>0</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>\n" +
                                               "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>0</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>\n" +
                                               "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>0</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>\n" +
                                           "</Variables>\n" +
                                           "<Categories />\n" +
                                       "</OUT_APPL_DEC_OFFERS>\n" +
                                       "<OUT_APPL_PDN>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_PDN_CALC_DATE>"+IN_APPL_REQUEST_DATE+"</OUT_APPL_PDN_CALC_DATE>\n" +
                                               "<OUT_APPL_PDN_CONFIRMED_INCOME>0</OUT_APPL_PDN_CONFIRMED_INCOME>\n" +
                                               "<OUT_APPL_PDN_INCOME_PER_PERSON>39374.75</OUT_APPL_PDN_INCOME_PER_PERSON>\n" +
                                               "<OUT_APPL_PDN_CH_INCOME>0</OUT_APPL_PDN_CH_INCOME>\n" +
                                               "<OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>0</OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>\n" +
                                               "<OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>0.0761909599426</OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>\n" +
                                               "<OUT_APPL_PDN_VALUE_BY_CH_INCOME>0</OUT_APPL_PDN_VALUE_BY_CH_INCOME>\n" +
                                               "<OUT_APPL_PDN_MIN_VALUE>0.0761909599426</OUT_APPL_PDN_MIN_VALUE>\n" +
                                               "<OUT_APPL_PDN_CH_EXPENSES>3000</OUT_APPL_PDN_CH_EXPENSES>\n" +
                                               "<OUT_APPL_PDN_INCOME_FOR_PDN_CALC>39374.75</OUT_APPL_PDN_INCOME_FOR_PDN_CALC>\n" +
                                               "<OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>1</OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>\n" +
                                               "<OUT_APPL_PDN_TOTALINCOME>"+IN_CUSTOMER_BASICSALARY+"</OUT_APPL_PDN_TOTALINCOME>\n" +
                                           "</Variables>\n" +
                                           "<Categories>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>"+IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnLimitCLIP</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnExpense</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>3000</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnIncomeType</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>per_person</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnNotConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>39374.75</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnImputedIncome</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnValue</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0.0762</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnOfferPSK</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnOfferPmt</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>3000</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnBkiStartDate</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>2024-05-01</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnBkiEndDate</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>2022-05-01</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>pdnBkiMnthCnt</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>18</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_1</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_2</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_3</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_4</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_5</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_6</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_7</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_8</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_9</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_10</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_11</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_12</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_13</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_14</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_15</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_16</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_17</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_18</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_19</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_20</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_21</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_22</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_23</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                               "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                   "<Variables>\n" +
                                                       "<COMMON_PROPERTY_NAME>bkiPmtMnth_24</COMMON_PROPERTY_NAME>\n" +
                                                       "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                       "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                   "</Variables>\n" +
                                               "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                           "</Categories>\n" +
                                       "</OUT_APPL_PDN>\n" +
                                       "<OUT_APPL_DEC_RULES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_RS_CODE>TECH_RULE_4</OUT_APPL_DEC_RS_CODE>\n" +
                                               "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                               "<OUT_APPL_DEC_RS_NAME>WalkIn_NTB</OUT_APPL_DEC_RS_NAME>\n" +
                                               "<OUT_APPL_DEC_RS_DESCRIPTION>WalkIn_NTB</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_RULES>\n" +
                                       "<OUT_APPL_DEC_RULES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_RS_CODE>ND013</OUT_APPL_DEC_RS_CODE>\n" +
                                               "<OUT_APPL_DEC_RS_TYPE>I</OUT_APPL_DEC_RS_TYPE>\n" +
                                               "<OUT_APPL_DEC_RS_NAME>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_NAME>\n" +
                                               "<OUT_APPL_DEC_RS_DESCRIPTION>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_RULES>\n" +
                                       "<OUT_APPL_DEC_RULES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_RS_CODE>SCR_RULE_2</OUT_APPL_DEC_RS_CODE>\n" +
                                               "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                               "<OUT_APPL_DEC_RS_NAME>NTB_S_4$Частная (ЗАО/АО)_0 .. 5_1$Холост / Не замужем_1_3</OUT_APPL_DEC_RS_NAME>\n" +
                                               "<OUT_APPL_DEC_RS_DESCRIPTION>4_55_0.1818$55$Республика Башкортостан_1</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_RULES>\n" +
                                       "<OUT_APPL_DEC_RULES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_RS_CODE>SCR_RULE_1</OUT_APPL_DEC_RS_CODE>\n" +
                                               "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                               "<OUT_APPL_DEC_RS_NAME>-2.8276545449435124,-6.162640877954405,0.002102253647013393</OUT_APPL_DEC_RS_NAME>\n" +
                                               "<OUT_APPL_DEC_RS_DESCRIPTION>1,-5.338589376383459,0.004779685540496534,0</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_RULES>\n" +
                                       "<OUT_APPL_DEC_RULES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_RS_CODE>SCR_RULE_0</OUT_APPL_DEC_RS_CODE>\n" +
                                               "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                               "<OUT_APPL_DEC_RS_NAME>1:WalkIn_NTB</OUT_APPL_DEC_RS_NAME>\n" +
                                               "<OUT_APPL_DEC_RS_DESCRIPTION>1:0.0056494628297687486</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_RULES>\n" +
                                       "<OUT_APPL_DEC_RULES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_RS_CODE>ND013</OUT_APPL_DEC_RS_CODE>\n" +
                                               "<OUT_APPL_DEC_RS_TYPE>I</OUT_APPL_DEC_RS_TYPE>\n" +
                                               "<OUT_APPL_DEC_RS_NAME>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_NAME>\n" +
                                               "<OUT_APPL_DEC_RS_DESCRIPTION>В блоке НСИ отсутствует полный перечень нормирующих констант</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_RULES>\n" +
                                       "<OUT_APPL_DEC_RULES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_RS_CODE>S1_VER</OUT_APPL_DEC_RS_CODE>\n" +
                                               "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                               "<OUT_APPL_DEC_RS_NAME>S1_VER</OUT_APPL_DEC_RS_NAME>\n" +
                                               "<OUT_APPL_DEC_RS_DESCRIPTION>L21_V293</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_RULES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>cus_prj_group</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE />\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>limitUpSegment</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR />\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE />\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>lib_PDN</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>libVersion</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>v1.21</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>v_INQUIRY1WEEK_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>10.23287</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>v_INQUIRY1MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>26.286556</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>v_INQUIRY3MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>56.445345</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>v_INQUIRY6MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>86.080923</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>v_INQUIRY9MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>103.887847</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>v_INQUIRY12MONTH_avg</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>126.401192</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>probability_1</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0.03980353136081286</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>pd_BKI_CCNTB_v1</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0.03980353136081286</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>score_BKI_CCNTB_v1</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>-3.1831822823424845</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>megPD</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0.018098</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>score_OFv3</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>-3.993690072111527</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>score_BKI_CCNTB_v1_calibr</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>-1.9206879851896734</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>score2_BKI_CCNTBv1_meg</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>-3.597614476462521</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>score2_BKI_CCNTBv1_meg_calibrated</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>-2.660275969665012</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>pd_BKI_CCNTB_v1_calibr</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0.12778486667624236</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>pd_BKI_CCNTBv1_meg_calibrated</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0.06535847331526524</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>BKI_NEW_MEG</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>6-</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>PD_OLD</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>BKI_NEW_MEG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0.005649462829768749</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>limitsDebug</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>WalkIn_NTB 23-67 BKIHIT pilotNew Max_Limit1 6-</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>limitsDebugUp</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>WalkIn_NTB 23-67 BKIHIT pilotNew Max_Limit1 5-</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>cusCCNTBPD</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR />\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE />\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0.06535847331526524</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>PDN_CC_FLAG 0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>PDN_CC_LIMIT 0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>mplPdnCCLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>mplPdnCCFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>pdnIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>39374.75</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>pdnSpendMax</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>19687.375</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>pdnSpendCurrent</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>offer_1</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR />\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE />\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>1 100000 0 0 0 100000 100000 9999 110 NULL 1 0.209 0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_CREDITPAYEXP</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>VAR_CUS_BKI_OVERVIEW_MONTHLY_PAYMENT</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>5400</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_OTHEREXP</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>VAR_CUSTOMER_MIN_LIVING_EXP</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>8643</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>100000</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit*0.04</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>4000</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>cusTotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>"+IN_CUSTOMER_BASICSALARY+"</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>pti</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>0.0768</OUT_APPL_DEC_VAR_DESCR>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>ITERNUM</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>Номер вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>2</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>S1CallDateTime</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>Дата и время вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>"+LONG_DATA+"</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>applPSKCalcEnabledFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>Флаг расчёта и контроля ПСК в стратегии</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>applPresaleNavigatorFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>Флаг процесса FA / MA NAVIGATOR</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>Segment</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>WalkIn_NTB</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>cusWorkerType</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>Other</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MaxPTIByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>1.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MaxLimitByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>100000</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MinLivingExp</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>TotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>"+IN_CUSTOMER_BASICSALARY+".00</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>Score_Raw</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>-5.338589376383459</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>Score_Scaled</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>241.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>PD</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0.06535847331526524</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>RiskLevel</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MasterGrade</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>6-</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>PrevappMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0.00</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>FccreditMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>BkiMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>5400.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>BkiMonthlyPayment (не учитывать)</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>DepCount</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>RegionMinSalary (ПМ)</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>8643</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>RAPID_CLIP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>BKI_HIT_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>1</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MKK_PD</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0.1438122751059222</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MKK_score</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>-1.783980852887030</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>OFFER with ID: 1</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>NULL</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>IsMain:1, Limit:100000, MinLimit:9999, Rate:0.209</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>LIMIT_UP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>LIMITUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>11</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW_TECH</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>11</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>"+IN_APPL_DEC_VAR_VALUE+"</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                       "<OUT_APPL_DEC_VARIABLES>\n" +
                                           "<Variables>\n" +
                                               "<OUT_APPL_DEC_VAR_NAME>MKK_DECISION</OUT_APPL_DEC_VAR_NAME>\n" +
                                               "<OUT_APPL_DEC_VAR_DESCR>MKK_VARS_GROUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                                               "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                               "<OUT_APPL_DEC_VAR_VALUE>DECLINE</OUT_APPL_DEC_VAR_VALUE>\n" +
                                           "</Variables>\n" +
                                       "</OUT_APPL_DEC_VARIABLES>\n" +
                                   "</Categories>\n" +
                               "</Application>\n" +
                           "</Body>\n" +
                       "</StrategyOneResponseT5>\n" +
                "</ConnectorOutput>").toString();

        return ResponseEntity.ok(main_response);
        } else {//DS_05_51_Spark_GetCompanyStructure
            String INN=extract(rq,"<con1:inn xmlns:con1=\"http://connector.xws.mbtc.ru\">","</con1:inn>");
            String current_date=extract(rq,"<con1:dateOfReport xmlns:con1=\"http://connector.xws.mbtc.ru\">","T");
            return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<ConnectorOutput>\n" +
                    "<Response>\n" +
                    "<CompanyStructureReport>\n" +
                    "<ResultInfo ResultType=\"True\" ExecutionTime=\"30\" />\n" +
                    "<Data>\n" +
                    "<Report>\n" +
                    "<SparkID>6074001</SparkID>\n" +
                    "<ShortName>АО \"БАШКИРСКОЕ МОЛОКО\"</ShortName>\n" +
                    "<INN>"+INN+"</INN>\n" +
                    "<OGRN>1070273004713</OGRN>\n" +
                    "<OKPO>82047250</OKPO>\n" +
                    "<OKATO>80401385000</OKATO>\n" +
                    "<EGRPOIncluded>true</EGRPOIncluded>\n" +
                    "<Status IsActing=\"true\" Code=\"24\" Text=\"Действующее\" GroupId=\"1\" GroupName=\"Действующее\" Date=\""+current_date+"\" />\n" +
                    "</Report>\n" +
                    "</Data>\n" +
                    "</CompanyStructureReport>\n" +
                    "<ResultMsg>\n" +
                    "<ResultCode>1</ResultCode>\n" +
                    "<ResultMessage>DONE. </ResultMessage>\n" +
                    "</ResultMsg>\n" +
                    "</Response>\n" +
                    "</ConnectorOutput>");

        }

    }

    @GetMapping(value = "/Process/Business/Consumer/version10/mi_so_ABS_CLIENT_Acc_Req", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_02_35_CRM(HttpServletRequest request) {
        String rq = request.toString();
        String IDRequest =extract(rq,"<IDRequest>","</IDRequest>");
        String Customer_ID=extract(rq,"<Customer_ID>","</Customer_ID>");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date();
        String RequestDate = dateFormat.format(date);
        String IssueDate=extract(rq,"<Date_of_Cert>","</Date_of_Cert>");


        String main_response=("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                  "<ABS_CLIENT_Response>\n" +
                      "<ProcessID>CREDIT_CARDS</ProcessID>\n" +
                      "<ProcessName />\n" +
                      "<ABS_CLIENT_Response>\n" +
                          "<IDrequest>"+IDRequest+"_1</IDrequest>\n" +
                          "<RequestDate>"+RequestDate+"</RequestDate>\n" +
                          "<SM1 />\n" +
                          "<Applicant_Info>\n" +
                              "<Applicant>\n" +
                                  "<Customer_ID>"+Customer_ID+"</Customer_ID>\n" +
                                  "<IR_ID>188459923</IR_ID>\n" +
                                  "<Employee>0</Employee>\n" +
                                  "<EmployeeBank>0</EmployeeBank>\n" +
                                  "<CharVip>0</CharVip>\n" +
                                  "<VidCl>Клиент</VidCl>\n" +
                                  "<Gender>Ж</Gender>\n" +
                                  "<BirthPlace>С.САФАРОВО ЧИШМИНСКОГО РАЙОНА Р. БАШКОРТОСТАН</BirthPlace>\n" +
                                  "<IssueDate>"+IssueDate+" 00:00:00</IssueDate>\n" +
                                  "<IssuedBy>КОГАЛЫМСКИМ ГОРОДСКИМ ОТДЕЛОМ ВНУТРЕННИХ ДЕЛ УВД ХАНТЫ-МАНСИЙСКОГО АВТОНОМНОГО ОКРУГА ТЮМЕНСКОЙ ОБЛАСТИ</IssuedBy>\n" +
                                  "<DepCode>862-004</DepCode>\n" +
                                  "<SNILS>12131078395</SNILS>\n" +
                                  "<INN>025000546183</INN>\n" +
                                  "<OID />\n" +
                                  "<IdentSign />\n" +
                                  "<ChangeContactInfoHistory>\n" +
                                      "<ChangeContactInfo>\n" +
                                          "<RetailObj>784266782760</RetailObj>\n" +
                                          "<DateChange>2023-06-30 16:41:22</DateChange>\n" +
                                          "<Manager>КОРБАКОВА ИРИНА ИВАНОВНА</Manager>\n" +
                                          "<Attribute>PHONE 1</Attribute>\n" +
                                          "<OldData />\n" +
                                          "<NewData>9822212476</NewData>\n" +
                                      "</ChangeContactInfo>\n" +
                                  "</ChangeContactInfoHistory>\n" +
                                  "<CategoryClient>PUB</CategoryClient>\n" +
                                  "<CategorySalary />\n" +
                                  "<PacketInfo />\n" +
                                  "<DepositInfo>\n" +
                                      "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                                      "<AccountNumDep>42301810400990206494</AccountNumDep>\n" +
                                      "<DepositArrest>0</DepositArrest>\n" +
                                      "<VidDogDep>(001) До востребования (RUB)</VidDogDep>\n" +
                                      "<DateBeginDep>2003-12-23 00:00:00</DateBeginDep>\n" +
                                      "<DateEndDep>2013-11-25 00:00:00</DateEndDep>\n" +
                                      "<SumDogDep>405.55</SumDogDep>\n" +
                                      "<PeriodDep>3625</PeriodDep>\n" +
                                      "<CurDep>810</CurDep>\n" +
                                      "<BalanceDep>0.00</BalanceDep>\n" +
                                      "<StateDep>Закрыт</StateDep>\n" +
                                      "<FlagOver>0</FlagOver>\n" +
                                      "<NewCardAccount />\n" +
                                      "<BranchNewCardAccount />\n" +
                                      "<EnrollmentStatusCod>0</EnrollmentStatusCod>\n" +
                                  "</DepositInfo>\n" +
                                  "<DepositInfo>\n" +
                                      "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                                      "<AccountNumDep>40817810300961003197</AccountNumDep>\n" +
                                      "<DepositArrest>0</DepositArrest>\n" +
                                      "<VidDogDep>(099) Счет для кредита (RUB)</VidDogDep>\n" +
                                      "<DateBeginDep />\n" +
                                      "<DateEndDep />\n" +
                                      "<SumDogDep>0.00</SumDogDep>\n" +
                                      "<PeriodDep />\n" +
                                      "<CurDep>810</CurDep>\n" +
                                      "<BalanceDep>0.00</BalanceDep>\n" +
                                      "<StateDep>Закрыт</StateDep>\n" +
                                      "<FlagOver>0</FlagOver>\n" +
                                      "<NewCardAccount />\n" +
                                      "<BranchNewCardAccount />\n" +
                                      "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                                  "</DepositInfo>\n" +
                                  "<DepositInfo>\n" +
                                      "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                                      "<AccountNumDep>40817810500329066811</AccountNumDep>\n" +
                                      "<DepositArrest>0</DepositArrest>\n" +
                                      "<VidDogDep>(001) Карточный вклад (RUB)</VidDogDep>\n" +
                                      "<DateBeginDep>2023-08-11 00:00:00</DateBeginDep>\n" +
                                      "<DateEndDep />\n" +
                                      "<SumDogDep>0.00</SumDogDep>\n" +
                                      "<PeriodDep />\n" +
                                      "<CurDep>810</CurDep>\n" +
                                      "<BalanceDep>0.00</BalanceDep>\n" +
                                      "<StateDep>Работает</StateDep>\n" +
                                      "<FlagOver>1</FlagOver>\n" +
                                      "<NewCardAccount>40817810500329066811</NewCardAccount>\n" +
                                      "<BranchNewCardAccount>001</BranchNewCardAccount>\n" +
                                      "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                                  "</DepositInfo>\n" +
                                  "<DepositInfo>\n" +
                                      "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                                      "<AccountNumDep>40817810400329066610</AccountNumDep>\n" +
                                      "<DepositArrest>0</DepositArrest>\n" +
                                      "<VidDogDep>(001) Счет для кредита (RUB)</VidDogDep>\n" +
                                      "<DateBeginDep>2023-08-02 00:00:00</DateBeginDep>\n" +
                                      "<DateEndDep />\n" +
                                      "<SumDogDep>0.00</SumDogDep>\n" +
                                      "<PeriodDep />\n" +
                                      "<CurDep>810</CurDep>\n" +
                                      "<BalanceDep>0.00</BalanceDep>\n" +
                                      "<StateDep>Работает</StateDep>\n" +
                                      "<FlagOver>1</FlagOver>\n" +
                                      "<NewCardAccount>40817810400329066610</NewCardAccount>\n" +
                                      "<BranchNewCardAccount>001</BranchNewCardAccount>\n" +
                                      "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                                  "</DepositInfo>\n" +
                                  "<DepositInfo>\n" +
                                      "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                                      "<AccountNumDep>40817810200329066771</AccountNumDep>\n" +
                                      "<DepositArrest>0</DepositArrest>\n" +
                                      "<VidDogDep>(001) Карточный счет ПРИБЫЛЬ NEW (RUB)</VidDogDep>\n" +
                                      "<DateBeginDep>2023-08-02 00:00:00</DateBeginDep>\n" +
                                      "<DateEndDep>2023-09-24 00:00:00</DateEndDep>\n" +
                                      "<SumDogDep>0.00</SumDogDep>\n" +
                                      "<PeriodDep>53</PeriodDep>\n" +
                                      "<CurDep>810</CurDep>\n" +
                                      "<BalanceDep>0.00</BalanceDep>\n" +
                                      "<StateDep>Закрыт</StateDep>\n" +
                                      "<FlagOver>0</FlagOver>\n" +
                                      "<NewCardAccount />\n" +
                                      "<BranchNewCardAccount />\n" +
                                      "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                                  "</DepositInfo>\n" +
                                  "<DepositInfo>\n" +
                                      "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                                      "<AccountNumDep>40817810200329066810</AccountNumDep>\n" +
                                      "<DepositArrest>0</DepositArrest>\n" +
                                      "<VidDogDep>(001) Карточный вклад (RUB)</VidDogDep>\n" +
                                      "<DateBeginDep />\n" +
                                      "<DateEndDep />\n" +
                                      "<SumDogDep>599.00</SumDogDep>\n" +
                                      "<PeriodDep>0</PeriodDep>\n" +
                                      "<CurDep>810</CurDep>\n" +
                                      "<BalanceDep>0.00</BalanceDep>\n" +
                                      "<StateDep>Заморожен</StateDep>\n" +
                                      "<FlagOver>0</FlagOver>\n" +
                                      "<NewCardAccount />\n" +
                                      "<BranchNewCardAccount />\n" +
                                      "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                                  "</DepositInfo>\n" +
                                  "<DepositInfo>\n" +
                                      "<AccountTypeDep>Обычный</AccountTypeDep>\n" +
                                      "<AccountNumDep>40817810400961003194</AccountNumDep>\n" +
                                      "<DepositArrest>0</DepositArrest>\n" +
                                      "<VidDogDep>(099) Счет для кредита (RUB)</VidDogDep>\n" +
                                      "<DateBeginDep />\n" +
                                      "<DateEndDep />\n" +
                                      "<SumDogDep>0.00</SumDogDep>\n" +
                                      "<PeriodDep />\n" +
                                      "<CurDep>810</CurDep>\n" +
                                      "<BalanceDep>0.00</BalanceDep>\n" +
                                      "<StateDep>Закрыт</StateDep>\n" +
                                      "<FlagOver>0</FlagOver>\n" +
                                      "<NewCardAccount />\n" +
                                      "<BranchNewCardAccount />\n" +
                                      "<EnrollmentStatusCod>1</EnrollmentStatusCod>\n" +
                                  "</DepositInfo>\n" +
                                  "<LimitOrders>0</LimitOrders>\n" +
                                  "<CreditInfo>\n" +
                                      "<Credit>\n" +
                                          "<CreditNumDog>0032-2Z3/00035</CreditNumDog>\n" +
                                          "<CreditDogID>798897151281</CreditDogID>\n" +
                                          "<DateBeginCredit>2023-08-02 00:00:00</DateBeginCredit>\n" +
                                          "<TypeClientCredit>1</TypeClientCredit>\n" +
                                          "<DateEndCredit>2024-09-02 00:00:00</DateEndCredit>\n" +
                                          "<NameKindCredit>9#2Z#Потребительский кредит под залог ТС с отлагательным условием</NameKindCredit>\n" +
                                          "<DateCloseCredit>2024-03-29 00:00:00</DateCloseCredit>\n" +
                                          "<LimitCredit>0.0</LimitCredit>\n" +
                                          "<OstSumCredit>0.0</OstSumCredit>\n" +
                                          "<ValutaCred>810</ValutaCred>\n" +
                                          "<PrcCredit>5.9</PrcCredit>\n" +
                                          "<ProsrSumCredit>0.0</ProsrSumCredit>\n" +
                                          "<StatusCredit>13</StatusCredit>\n" +
                                          "<PaySummaCredit>0.0</PaySummaCredit>\n" +
                                          "<DateLastPay>2024-03-29 00:00:00</DateLastPay>\n" +
                                          "<AccountCredit>45506810300321001296</AccountCredit>\n" +
                                          "<UidCreditDog>7956b568-8060-1021-80be-a5dcbc70a03d-9</UidCreditDog>\n" +
                                          "<VidProduct>KRED_PERS_NK</VidProduct>\n" +
                                          "<PacketType />\n" +
                                          "<AnonymCard />\n" +
                                          "<FlagGP>0</FlagGP>\n" +
                                          "<Cobrand />\n" +
                                          "<CardPaySystem />\n" +
                                          "<PlasticType />\n" +
                                          "<SpecialOffer />\n" +
                                          "<CardAccount />\n" +
                                          "<BranchCardAccount />\n" +
                                          "<DateReport>"+RequestDate+"</DateReport>\n" +
                                          "<MannerOfPayment>111E1100</MannerOfPayment>\n" +
                                          "<MaxSumDelay>51720.00</MaxSumDelay>\n" +
                                          "<DateUnloading>2024-03-29 00:00:00</DateUnloading>\n" +
                                          "<ZalogInfo />\n" +
                                      "</Credit>\n" +
                                    "</CreditInfo>\n" +
                                    "<ClientSegment>Mass Retail</ClientSegment>\n" +
                                    "<ClientSegmentFAS>Mass</ClientSegmentFAS>\n" +
                                    "<ResourcePortfolio>0</ResourcePortfolio>\n" +
                                "</Applicant>\n" +
                            "</Applicant_Info>\n" +
                        "</ABS_CLIENT_Response>\n" +
                        "<ResultMsg>\n" +
                            "<ResultCode>1</ResultCode>\n" +
                            "<ResultMessage>OK. </ResultMessage>\n" +
                        "</ResultMsg>\n" +
                    "</ABS_CLIENT_Response>\n" +
                "</ConnectorOutput>").toString();
        return ResponseEntity.ok(main_response);
    }

    @GetMapping(value = "/ProductCatalogueRomashka_Service.serviceagent/ProductCatalogueEndpoint0", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_01_05_GetTariffs(HttpServletRequest request){
        String rq = request.toString();
        String ApplicationID=extract(rq,"<ApplicationID>","</ApplicationID>");
        String CurrentDate=extract(rq,"<CurrentDate>","</CurrentDate>");
        int[] n={1,3,3,5};
        Random rand = new Random();
        int sec=n[rand.nextInt(n.length)];
        long timer=sec*1000;
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        String main_response=("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<ProductCatalogueInquiryResponse>\n" +
                        "<ProcessID>CREDIT_CARDS</ProcessID>\n" +
                        "<ProcessName>CRDS</ProcessName>\n" +
                        "<ProductCatalogueResponse>\n" +
                            "<ApplicationID>"+ApplicationID+"</ApplicationID>\n" +
                            "<ExchangeRateList>\n" +
                                "<ExchangeRate>\n" +
                                    "<Currency>840</Currency>\n" +
                                    "<Rate>74.2529</Rate>\n" +
                                    "<Rate_Date>2020-12-07</Rate_Date>\n" +
                                "</ExchangeRate>\n" +
                                "<ExchangeRate>\n" +
                                    "<Currency>978</Currency>\n" +
                                    "<Rate>90.2618</Rate>\n" +
                                    "<Rate_Date>2020-12-07</Rate_Date>\n" +
                                "</ExchangeRate>\n" +
                                "<ExchangeRate>\n" +
                                    "<Currency>810</Currency>\n" +
                                    "<Rate>1</Rate>\n" +
                                    "<Rate_Date>"+CurrentDate+"</Rate_Date>\n" +
                                "</ExchangeRate>\n" +
                            "</ExchangeRateList>\n" +
                            "<RegionParameters>\n" +
                                "<Customer_ID>\n" +
                                "</Customer_ID>\n" +
                                "<Min_Living_Cost_in_Region_Living_RUR>\n" +
                              "</Min_Living_Cost_in_Region_Living_RUR>\n" +
                              "<Average_Wage_in_Region_of_Employer_RUR>\n" +
                              "</Average_Wage_in_Region_of_Employer_RUR>\n" +
                          "</RegionParameters>\n" +
                      "</ProductCatalogueResponse>\n" +
                      "<ResultMsg>\n" +
                          "<ResultCode>8</ResultCode>\n" +
                          "<ResultMessage>По запрошенным параметрам определить тариф не удалось</ResultMessage>\n" +
                      "</ResultMsg>\n" +
                      "<CurrentDate>"+CurrentDate+"</CurrentDate>\n" +
                  "</ProductCatalogueInquiryResponse>\n" +
                "</ConnectorOutput>").toString();
        return ResponseEntity.ok(main_response);
    }

    @GetMapping(value = "/Get_CreditHistory", produces = "multipart/form-data;charset=UTF-8")//в комментариях приписка "плохой"
    public ResponseEntity DS_02_05_ExternalCH(HttpServletRequest request){ //тут же и DS_06_10_NationalHunterAndFPS
        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<ResultMsg>\n" +
                        "<ResultCode>1</ResultCode>\n" +
                        "<ResultMessage />\n" +
                    "</ResultMsg>\n" +
                "</ConnectorOutput>");
    }

    @GetMapping(value = "/mi_S_so_sbch", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_02_15_InternalCH(HttpServletRequest request){
        String rq=request.toString();
        String Application_ID=extract(rq,"<Application_ID>","</Application_ID>");
        String CustomerID=extract(rq,"<CustomerID>","</CustomerID>");
        String FName=extract(rq,"<Name>","</Name>");
        String Surname=extract(rq,"<Surname>","</Surname>");
        String Patronymic=extract(rq,"<Patronymic>","</Patronymic>");
        String BirthDate=extract(rq,"<Date_of_Birth>","</Date_of_Birth>");

        String main_response=("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorInput configId=\"_sbkisync\" soapAction=\"http://sap.com/xi/WebService/soap1.1\">\n" +
                    "<InternalCHInput>\n" +
                        "<InternalCHInput>\n" +
                            "<Application_ID>"+Application_ID+"</Application_ID>\n" +
                            "<InternalCHCriteria>\n" +
                                "<CustomerID>"+CustomerID+"</CustomerID>\n" +
                                "<Surname>"+Surname+"</Surname>\n" +
                                "<Name>"+FName+"</Name>\n" +
                                "<Patronymic>"+Patronymic+"</Patronymic>\n" +
                                "<BirthDate>"+BirthDate+"</BirthDate>\n" +
                            "</InternalCHCriteria>\n" +
                        "</InternalCHInput>\n" +
                    "</InternalCHInput>\n" +
                "</ConnectorInput>").toString();
        return ResponseEntity.ok(main_response);
    }

    @GetMapping(value = "/ru/CrifServices.asmx", produces = "multipart/form-data;charset=UTF-8")//уточнить корректность
    public ResponseEntity DS_02_45_GetPFRHistoryInfo(HttpServletRequest request){

        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                                        "<ConnectorOutput/>");


        }
    @GetMapping(value = "/CRDS", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_03_10_S1_PCM_TIBCO(HttpServletRequest request){
            String rq=request.toString();
            String InquiryCode=extract(rq,"</InquiryCode>","");
            String ProcessCode = extract(rq,"<ProcessCode>","</ProcessCode>");
            String ProcessVersion=extract(rq,"<ProcessVersion>","</ProcessVersion>");
            String LayoutVersion = extract(rq,"<LayoutVersion>","</LayoutVersion>");
            String IN_IN_APPL_REQUEST_DATE=extract(rq,"<IN_APPL_REQUEST_DATE>","</IN_APPL_REQUEST_DATE>");
            String IN_CUS_PREVAPP_BASICINCOME=extract(rq,"<IN_CUS_PREVAPP_BASICINCOME>","</IN_CUS_PREVAPP_BASICINCOME>");
            String IN_APPL_DEC_VAR_VALUE=extract(rq,"<IN_APPL_DEC_VAR_VALUE>","</IN_APPL_DEC_VAR_VALUE>");
            Locale locale=Locale.US;
            DateFormat dateFormat = new SimpleDateFormat("E MMM d yyyy HH:mm:ss 'GMT'Z (z)",locale);
            Date date= new Date();
            String LONG_DATA = dateFormat.format(date);
            String main_response=("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<ConnectorOutput>\n" +
                      "<StrategyOneResponseT5>\n" +
                          "<Header>\n" +
                              "<InquiryCode>"+InquiryCode+"</InquiryCode>\n" +
                              "<ProcessCode>"+ProcessCode+"</ProcessCode>\n" +
                              "<OrganizationCode>CRDS</OrganizationCode>\n" +
                              "<ProcessVersion>"+ProcessVersion+"</ProcessVersion>\n" +
                              "<LayoutVersion>"+LayoutVersion+"</LayoutVersion>\n" +
                          "</Header>\n" +
                          "<Body>\n" +
                              "<Application>\n" +
                                  "<Variables>\n" +
                                      "<OUT_APPL_APPLICATIONID>"+InquiryCode+"</OUT_APPL_APPLICATIONID>\n" +
                                      "<OUT_APPL_REQUEST_TYPE>BASE</OUT_APPL_REQUEST_TYPE>\n" +
                                      "<OUT_APPL_DEC_RESULT>PREAPPROVE</OUT_APPL_DEC_RESULT>\n" +
                                      "<OUT_APPL_DEC_TIMEOUT>0</OUT_APPL_DEC_TIMEOUT>\n" +
                                      "<OUT_APPL_DEC_PD>0.04095995029315</OUT_APPL_DEC_PD>\n" +
                                      "<OUT_APPL_DEC_SCORE>177</OUT_APPL_DEC_SCORE>\n" +
                                      "<OUT_APPL_PTI>0.1128</OUT_APPL_PTI>\n" +
                                  "</Variables>\n" +
                                  "<Categories>\n" +
                                      "<CUSTOMER>\n" +
                                          "<Variables>\n" +
                                              "<OUT_CUSTOMER_ID>476844900</OUT_CUSTOMER_ID>\n" +
                                              "<OUT_CUSTOMER_PTI>0.1128</OUT_CUSTOMER_PTI>\n" +
                                              "<OUT_CUSTOMER_DEC_RESULT>ACCEPT</OUT_CUSTOMER_DEC_RESULT>\n" +
                                              "<OUT_CUSTOMER_DEC_PD>0.04095995029315</OUT_CUSTOMER_DEC_PD>\n" +
                                              "<OUT_CUSTOMER_DEC_SCORE>177</OUT_CUSTOMER_DEC_SCORE>\n" +
                                              "<OUT_CUSTOMER_DEC_CATEGORY>S</OUT_CUSTOMER_DEC_CATEGORY>\n" +
                                              "<OUT_CUSTOMER_DEC_CATEGORY_EXT>S</OUT_CUSTOMER_DEC_CATEGORY_EXT>\n" +
                                              "<OUT_CUSTOMER_DEC_CATEGORY_PC>S</OUT_CUSTOMER_DEC_CATEGORY_PC>\n" +
                                              "<OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>СКИ</OUT_CUSTOMER_DEC_CREDITHISTORY_TYPE>\n" +
                                              "<OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>0</OUT_CUSTOMER_DEC_CREDITHISTORY_POS_MAXLIMIT>\n" +
                                              "<OUT_CUSTOMER_TOTALINCOME>"+IN_CUS_PREVAPP_BASICINCOME+"</OUT_CUSTOMER_TOTALINCOME>\n" +
                                          "</Variables>\n" +
                                          "<Categories>\n" +
                                              "<OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_VERIFICATION_PART>CM_DOC_MAIN</OUT_CUSTOMER_DEC_VERIFICATION_PART>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_VERIFICATION>\n" +
                                              "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_CODE>INF_SCORE_NTB_IS_NULL </OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_NAME>NTB_PD не рассчитан</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>NTB_PD не рассчитан</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_RULES>\n" +
                                              "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_CODE>N154</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_NAME>Телефон из заявки не совпал с телефонами в БКИ ИЛИ в БКИ отсутствуют данные по телефонам</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>Телефон из заявки не совпал с телефонами в БКИ ИЛИ в БКИ отсутствуют данные по телефонам</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_RULES>\n" +
                                              "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_CODE>AppValue</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_TYPE>SI</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_NAME>Значение суммарного дохода участника сделки</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>"+IN_CUS_PREVAPP_BASICINCOME+".00</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_RULES>\n" +
                                              "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_CODE>S008_fsr</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_NAME>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>По осн. заемщику число запросов в БКИ за последние 3 месяца &lt;25. Если не было проверки ССБ по заявке. Кроме заемщиков Банка, сотрудников ФК и Банка</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_RULES>\n" +
                                              "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_CODE>CM005</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_NAME>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка основного документа (паспорт). Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_RULES>\n" +
                                              "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_CODE>CM006</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_NAME>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_DESCRIPTION>Проверка документов, подтверждающий доход. Результаты проверки сброшены</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_RULES>\n" +
                                              "<OUT_CUSTOMER_DEC_RULES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_CODE>CM011</OUT_CUSTOMER_DEC_RS_CODE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_TYPE>I</OUT_CUSTOMER_DEC_RS_TYPE>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_NAME> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_NAME>\n" +
                                                      "<OUT_CUSTOMER_DEC_RS_DESCRIPTION> Проверка документов: документ, косвенно подтверждающий доход. В случае предоставления.</OUT_CUSTOMER_DEC_RS_DESCRIPTION>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_CUSTOMER_DEC_RULES>\n" +
                                              "<OUT_CUSTOMER_DEC_SCORES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_DEC_SCORES_SCORE>-3.12544108082075</OUT_CUSTOMER_DEC_SCORES_SCORE>\n" +
                                                      "<OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>177</OUT_CUSTOMER_DEC_SCORES_SCORE_SCALED>\n" +
                                                      "<OUT_CUSTOMER_DEC_SCORES_PD>0.04095995029315</OUT_CUSTOMER_DEC_SCORES_PD>\n" +
                                                      "<OUT_CUSTOMER_DEC_SCORES_CUTOFF>0.163</OUT_CUSTOMER_DEC_SCORES_CUTOFF>\n" +
                                                      "<OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>110</OUT_CUSTOMER_DEC_SCORES_PRODUCT_ID>\n" +
                                                  "</Variables>\n" +
                                                  "<Categories />\n" +
                                              "</OUT_CUSTOMER_DEC_SCORES>\n" +
                                              "<OUT_CUSTOMER_SERVICES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_SERVICES_NAME>BIAS_FSSP</OUT_CUSTOMER_SERVICES_NAME>\n" +
                                                      "<OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                                                  "</Variables>\n" +
                                                  "<Categories />\n" +
                                              "</OUT_CUSTOMER_SERVICES>\n" +
                                              "<OUT_CUSTOMER_SERVICES>\n" +
                                                  "<Variables>\n" +
                                                      "<OUT_CUSTOMER_SERVICES_NAME>BIAS_NEGATIV</OUT_CUSTOMER_SERVICES_NAME>\n" +
                                                      "<OUT_CUSTOMER_SERVICES_STATUS>0</OUT_CUSTOMER_SERVICES_STATUS>\n" +
                                                  "</Variables>\n" +
                                                  "<Categories />\n" +
                                              "</OUT_CUSTOMER_SERVICES>\n" +
                                              "<OUT_CUSTOMER_PDN>\n" +
                                                  "<Variables />\n" +
                                                  "<Categories>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>"+IN_IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>SUBJECTID</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>476844900</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>cusType</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>Applicant</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn1</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplIsOwn0</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnBkiPmtSum_ApplRel5</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnDeclaredIncome</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnRetailIncome</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnModelingIncome</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                      "<OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                          "<Variables>\n" +
                                                              "<COMMON_PROPERTY_NAME>pdnIncomeCalcPeriod</COMMON_PROPERTY_NAME>\n" +
                                                              "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                              "<COMMON_PROPERTY_VALUE>12</COMMON_PROPERTY_VALUE>\n" +
                                                          "</Variables>\n" +
                                                      "</OUT_CUSTOMER_PDN_COMMON_PROPERTY>\n" +
                                                  "</Categories>\n" +
                                              "</OUT_CUSTOMER_PDN>\n" +
                                          "</Categories>\n" +
                                      "</CUSTOMER>\n" +
                                      "<OUT_APPL_DEC_OFFERS>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_OFFERS_ID>1</OUT_APPL_DEC_OFFERS_ID>\n" +
                                              "<OUT_APPL_DEC_OFFERS_CREDITLIMIT>29000</OUT_APPL_DEC_OFFERS_CREDITLIMIT>\n" +
                                              "<OUT_APPL_DEC_OFFERS_MINLIMIT>9999</OUT_APPL_DEC_OFFERS_MINLIMIT>\n" +
                                              "<OUT_APPL_DEC_OFFERS_APR>0.209</OUT_APPL_DEC_OFFERS_APR>\n" +
                                              "<OUT_APPL_DEC_OFFERS_PRODUCTID>110</OUT_APPL_DEC_OFFERS_PRODUCTID>\n" +
                                              "<OUT_APPL_DEC_OFFERS_MAIN>1</OUT_APPL_DEC_OFFERS_MAIN>\n" +
                                              "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>29000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_BANK>\n" +
                                              "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>30000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_PDN>\n" +
                                              "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>40000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP>\n" +
                                              "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>40000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_BANK>\n" +
                                              "<OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>30000</OUT_APPL_DEC_OFFERS_CREDITLIMIT_UP_PDN>\n" +
                                          "</Variables>\n" +
                                          "<Categories />\n" +
                                      "</OUT_APPL_DEC_OFFERS>\n" +
                                      "<OUT_APPL_PDN>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_PDN_CALC_DATE>"+IN_IN_APPL_REQUEST_DATE+"</OUT_APPL_PDN_CALC_DATE>\n" +
                                              "<OUT_APPL_PDN_CONFIRMED_INCOME>0</OUT_APPL_PDN_CONFIRMED_INCOME>\n" +
                                              "<OUT_APPL_PDN_INCOME_PER_PERSON>46307</OUT_APPL_PDN_INCOME_PER_PERSON>\n" +
                                              "<OUT_APPL_PDN_CH_INCOME>0</OUT_APPL_PDN_CH_INCOME>\n" +
                                              "<OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>0</OUT_APPL_PDN_VALUE_BY_CONFIRMED_INCOME>\n" +
                                              "<OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>0.01878765629386</OUT_APPL_PDN_VALUE_BY_INCOME_PER_PERSON>\n" +
                                              "<OUT_APPL_PDN_VALUE_BY_CH_INCOME>0</OUT_APPL_PDN_VALUE_BY_CH_INCOME>\n" +
                                              "<OUT_APPL_PDN_MIN_VALUE>0.01878765629386</OUT_APPL_PDN_MIN_VALUE>\n" +
                                              "<OUT_APPL_PDN_CH_EXPENSES>870</OUT_APPL_PDN_CH_EXPENSES>\n" +
                                              "<OUT_APPL_PDN_INCOME_FOR_PDN_CALC>46307</OUT_APPL_PDN_INCOME_FOR_PDN_CALC>\n" +
                                              "<OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>1</OUT_APPL_PDN_CALCULATED_WITH_INCPERPERSON_FLAG>\n" +
                                              "<OUT_APPL_PDN_TOTALINCOME>"+IN_CUS_PREVAPP_BASICINCOME+"</OUT_APPL_PDN_TOTALINCOME>\n" +
                                          "</Variables>\n" +
                                          "<Categories>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnCalculationDate</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>"+IN_IN_APPL_REQUEST_DATE+"</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnLimitCLIP</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnExpense</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>870</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnIncomeType</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>per_person</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnAvgMonthlyIncome</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnNotConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnConfirmedIncome</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnPerPersonIncome</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>46307</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnImputedIncome</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnValue</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0.0188</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnOfferPSK</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnOfferPmt</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>870</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnBkiIncome</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnBkiPmt</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnBkiStartDate</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>2024-05-01</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnBkiEndDate</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>2022-05-01</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>pdnBkiMnthCnt</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>18</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_1</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_2</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_3</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_4</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_5</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_6</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_7</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_8</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_9</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_10</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_11</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_12</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_13</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_14</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_15</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_16</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_17</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_18</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_19</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_20</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_21</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_22</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_23</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                              "<OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                                  "<Variables>\n" +
                                                      "<COMMON_PROPERTY_NAME>bkiPmtMnth_24</COMMON_PROPERTY_NAME>\n" +
                                                      "<COMMON_PROPERTY_TYPE>STRING</COMMON_PROPERTY_TYPE>\n" +
                                                      "<COMMON_PROPERTY_VALUE>0</COMMON_PROPERTY_VALUE>\n" +
                                                  "</Variables>\n" +
                                              "</OUT_APPL_PDN_COMMON_PROPERTY>\n" +
                                          "</Categories>\n" +
                                      "</OUT_APPL_PDN>\n" +
                                      "<OUT_APPL_DEC_RULES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_RS_CODE>TECH_RULE_4</OUT_APPL_DEC_RS_CODE>\n" +
                                              "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                              "<OUT_APPL_DEC_RS_NAME>WalkIn_EXIST</OUT_APPL_DEC_RS_NAME>\n" +
                                              "<OUT_APPL_DEC_RS_DESCRIPTION>WalkIn_EXIST</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_RULES>\n" +
                                      "<OUT_APPL_DEC_RULES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_RS_CODE>SCR_RULE_2</OUT_APPL_DEC_RS_CODE>\n" +
                                              "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                              "<OUT_APPL_DEC_RS_NAME>EXIST_S_3$Частная (ЗАО/АО)_6 .. 10_1$Холост / Не замужем_0_2</OUT_APPL_DEC_RS_NAME>\n" +
                                              "<OUT_APPL_DEC_RS_DESCRIPTION>4_41_0.1818$41$Новосибирская область_0</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_RULES>\n" +
                                      "<OUT_APPL_DEC_RULES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_RS_CODE>SCR_RULE_1</OUT_APPL_DEC_RS_CODE>\n" +
                                              "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                              "<OUT_APPL_DEC_RS_NAME>-3.28952083053102,0,0</OUT_APPL_DEC_RS_NAME>\n" +
                                              "<OUT_APPL_DEC_RS_DESCRIPTION>0,-3.1254410808207505,0.04206994873765842,0</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_RULES>\n" +
                                      "<OUT_APPL_DEC_RULES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_RS_CODE>SCR_RULE_0</OUT_APPL_DEC_RS_CODE>\n" +
                                              "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                              "<OUT_APPL_DEC_RS_NAME>1:WalkIn_EXIST</OUT_APPL_DEC_RS_NAME>\n" +
                                              "<OUT_APPL_DEC_RS_DESCRIPTION>1:0.04095995029315231</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_RULES>\n" +
                                      "<OUT_APPL_DEC_RULES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_RS_CODE>S1_VER</OUT_APPL_DEC_RS_CODE>\n" +
                                              "<OUT_APPL_DEC_RS_TYPE>SI</OUT_APPL_DEC_RS_TYPE>\n" +
                                              "<OUT_APPL_DEC_RS_NAME>S1_VER</OUT_APPL_DEC_RS_NAME>\n" +
                                              "<OUT_APPL_DEC_RS_DESCRIPTION>L21_V293</OUT_APPL_DEC_RS_DESCRIPTION>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_RULES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>limitUpSegment</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR />\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE />\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>lib_PDN</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>libVersion</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>v1.21</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>limitsDebug</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>WalkIn_EXIST 23-67 NOHIT 6+</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>limitsDebugUp</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>DEBUG</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>WalkIn_EXIST 23-67 NOHIT 5+</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>cusCCNTBPD</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR />\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE />\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>1.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>PDN_CC_FLAG 0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MPL_PARAM</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>PDN_CC_LIMIT 0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>mplPdnCCLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>0.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>mplPdnCCFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>pdnIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>46307</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>pdnSpendMax</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>23153.5</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>pdnSpendCurrent</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>offer_1</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR />\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE />\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>1 29000 40000 40000 30000 29000 30000 9999 110 NULL 1 0.209 0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_CREDITPAYEXP</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>VAR_CUS_BKI_OVERVIEW_MONTHLY_PAYMENT</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>IN_CUSTOMER_OTHEREXP</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>0</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>VAR_CUSTOMER_MIN_LIVING_EXP</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>10117</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>29000</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>maxOffer.offerCreditLimit*0.04</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>1160</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>cusTotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>"+IN_CUS_PREVAPP_BASICINCOME+"</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>pti</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>0.1128</OUT_APPL_DEC_VAR_DESCR>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>ITERNUM</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>Номер вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>3</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>S1CallDateTime</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>Дата и время вызова S1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>"+LONG_DATA+"</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>applPSKCalcEnabledFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>Флаг расчёта и контроля ПСК в стратегии</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>applPresaleNavigatorFlag</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>Флаг процесса FA / MA NAVIGATOR</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>Segment</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>WalkIn_EXIST</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>cusWorkerType</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>Other</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MaxPTIByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>1.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MaxLimitByPD</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>30000</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MinLivingExp</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>TotalIncome</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>"+IN_CUS_PREVAPP_BASICINCOME+".00</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>Score_Raw</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>-3.125441080820750</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>Score_Scaled</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>177.0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>PD</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0.04095995029315231</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>RiskLevel</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MasterGrade</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1, TARIFF1, ТП 120 дней на максимум</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>6+</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>PrevappMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0.00</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>FccreditMonthlyPayment</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>DepCount</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>RegionMinSalary (ПМ)</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>10117</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>RAPID_CLIP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>1</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>BKI_HIT_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MKK_PD</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MKK_score</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>CUSTOMER1</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>NULL</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>OFFER with ID: 1</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>NULL</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>IsMain:1, Limit:29000, MinLimit:9999, Rate:0.209</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>LIMIT_UP_FLAG</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>LIMITUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>DIGITAL_LIMIT_PILOT_NEW_TECH</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>PILOT_NEW</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>NUMBER</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>0</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>IN_APPL_CREATE_DATE</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>"+IN_APPL_DEC_VAR_VALUE+"</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                      "<OUT_APPL_DEC_VARIABLES>\n" +
                                          "<Variables>\n" +
                                              "<OUT_APPL_DEC_VAR_NAME>MKK_DECISION</OUT_APPL_DEC_VAR_NAME>\n" +
                                              "<OUT_APPL_DEC_VAR_DESCR>MKK_VARS_GROUP</OUT_APPL_DEC_VAR_DESCR>\n" +
                                              "<OUT_APPL_DEC_VAR_TYPE>STRING</OUT_APPL_DEC_VAR_TYPE>\n" +
                                              "<OUT_APPL_DEC_VAR_VALUE>DECLINE</OUT_APPL_DEC_VAR_VALUE>\n" +
                                          "</Variables>\n" +
                                      "</OUT_APPL_DEC_VARIABLES>\n" +
                                  "</Categories>\n" +
                              "</Application>\n" +
                          "</Body>\n" +
                      "</StrategyOneResponseT5>\n" +
                    "</ConnectorOutput>").toString();

        return ResponseEntity.ok(main_response);
    }






    @GetMapping(value = "/CreditRegistry_GetStopList_IPOTEKA", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_06_20_StopList(HttpServletRequest request){
        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<ResultMsg>\n" +
                        "<ResultCode>1</ResultCode>\n" +
                        "<ResultMessage />\n" +
                    "</ResultMsg>\n" +
                "</ConnectorOutput>");
    }

    @GetMapping(value = "/WebServices/WsOperatorInfo.asmx", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_06_70_CheckPhoneNumbers(HttpServletRequest request){

        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<Response>\n" +
                        "<SearchRs MatchInfoType=\"RADD\">\n" +
                            "<ResultMsg>\n" +
                                "<ResultCode>1</ResultCode>\n" +
                                "<ResultMessage>DONE. No data found.</ResultMessage>\n" +
                            "</ResultMsg>\n" +
                        "</SearchRs>\n" +
                        "<SearchRs MatchInfoType=\"LADD\">\n" +
                            "<ResultMsg>\n" +
                                "<ResultCode>1</ResultCode>\n" +
                                "<ResultMessage>DONE. No data found.</ResultMessage>\n" +
                            "</ResultMsg>\n" +
                        "</SearchRs>\n" +
                        "<SearchRs MatchInfoType=\"ID\">\n" +
                            "<ResultMsg>\n" +
                                "<ResultCode>1</ResultCode>\n" +
                                "<ResultMessage>DONE. No data found.</ResultMessage>\n" +
                            "</ResultMsg>\n" +
                        "</SearchRs>\n" +
                        "<SearchRs MatchInfoType=\"FIO\">\n" +
                            "<ResultMsg>\n" +
                                "<ResultCode>1</ResultCode>\n" +
                                "<ResultMessage>DONE. No data found.</ResultMessage>\n" +
                            "</ResultMsg>\n" +
                        "</SearchRs>\n" +
                        "<SearchRs MatchInfoType=\"MPH\">\n" +
                            "<ResultMsg>\n" +
                                "<ResultCode>1</ResultCode>\n" +
                                "<ResultMessage>DONE. No data found.</ResultMessage>\n" +
                            "</ResultMsg>\n" +
                        "</SearchRs>\n" +
                        "<SearchRs MatchInfoType=\"WRKCMP\">\n" +
                            "<ResultMsg>\n" +
                                "<ResultCode>1</ResultCode>\n" +
                                "<ResultMessage>DONE. No data found.</ResultMessage>\n" +
                            "</ResultMsg>\n" +
                        "</SearchRs>\n" +
                    "</Response>\n" +
                "</ConnectorOutput>");
    }

    @GetMapping(value = "/WebServices/WsExternalCalendar.asmx", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_09_10_ExternalCalendar(HttpServletRequest request){ //здесь же и DS_12_10_ExternalCalendar

        String InputDateTime=extract(request.toString(),"InputDateTime=\"","\" Update_Cancel_TimeOut");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String rq=request.toString();
        String soapAction=extract(rq,"soapAction=\"http://tempuri.org/","\">");
        Date date;
        try {
            date = formatter.parse(InputDateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 5);
        Date dateWith5Days = cal.getTime();
        String date5= formatter.format(dateWith5Days);
        cal.add(Calendar.DATE, 60);
        Date dateWith60Days = cal.getTime();
        String date60= formatter.format(dateWith60Days);

        if (soapAction.equals("GetTimeoutInConcordanceWithExternalCalendarData_WithCancel")){
        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<GetTimeoutInConcordanceWithExternalCalendarData_WithCancelResponse>\n" +
                        "<ExternalCalendarOutputType WebUI_Id=\"CRDS_7100\" Cancel_TimeOut=\""+date60+"\" Exit_TimeOut=\""+date5+"\" Current_TimeOut=\""+date5+"\" Update_Cancel_TimeOut=\"0\" Type_Of_TimeOut=\"E\" />\n" +
                    "</GetTimeoutInConcordanceWithExternalCalendarData_WithCancelResponse>\n" +
                "</ConnectorOutput>");} else{ //DS_12_10_ExternalCalendar

            return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<ConnectorOutput>\n" +
                    "<GetIsWorkDayFlagFromExternalCalendarDataResponse>\n" +
                    "<GetIsWorkDayFlagFromExternalCalendarDataResult IsWorkDay=\"1\" />\n" +
                    "</GetIsWorkDayFlagFromExternalCalendarDataResponse>\n" +
                    "</ConnectorOutput>");

        }

    }

    @GetMapping(value = "/NcService.svc", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_14_10_NumberConversion(HttpServletRequest request){

        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<ConvertNumberToStringResponse>\n" +
                        "<ConvertNumberToStringResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Тридцать тысяч рублей 00 копеек</Result>\n" +
                                "<Tag>1</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Тридцать тысяч рублей 00 копеек</Result>\n" +
                                "<Tag>2</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Тридцать четыре целых девяносто сотых</Result>\n" +
                                "<Tag>5</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Тридцать тысяч рублей 00 копеек</Result>\n" +
                                "<Tag>13</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Ноль целых пятьдесят четыре тысячных</Result>\n" +
                                "<Tag>17</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Тридцать четыре целых восемьсот семьдесят две тысячных</Result>\n" +
                                "<Tag>19</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Ноль рублей 00 копеек</Result>\n" +
                                "<Tag>22-476832306</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Ноль рублей 00 копеек</Result>\n" +
                                "<Tag>24-476832306</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Ноль рублей 00 копеек</Result>\n" +
                                "<Tag>24-476832306</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Тридцать девять тысяч триста семьдесят четыре рубля 75 копеек</Result>\n" +
                                "<Tag>27</Tag>\n" +
                            "</SingleResult>\n" +
                            "<SingleResult>\n" +
                                "<CuId>999</CuId>\n" +
                                "<Result>Двадцать две тысячи семьсот тридцать семь рублей восемь копеек</Result>\n" +
                                "<Tag>100</Tag>\n" +
                            "</SingleResult>\n" +
                        "</ConvertNumberToStringResult>\n" +
                    "</ConvertNumberToStringResponse>\n" +
                "</ConnectorOutput>");
    }


    @GetMapping(value = "/CRIF_AFS_Request_Sync", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_16_20_AFS(HttpServletRequest request){
        String rq=request.toString();
        String appId=extract(rq,"<app:id>","</app:id>");
        String main_response=("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<CFResponse>\n" +
                        "<action>matchUpdate</action>\n" +
                        "<actionSNA>0</actionSNA>\n" +
                        "<sourceId>CreditFlow</sourceId>\n" +
                        "<ruleSetId>main</ruleSetId>\n" +
                        "<duration>55</duration>\n" +
                        "<appId>"+appId+"</appId>\n" +
                        "<appVersion>2</appVersion>\n" +
                        "<appsCount>10</appsCount>\n" +
                        "<rulesCount>3</rulesCount>\n" +
                        "<matchCount>19</matchCount>\n" +
                        "<queueStatus>0</queueStatus>\n" +
                        "<matchResult>\n" +
                            "<match>\n" +
                                "<appId>"+appId+"</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>BANK_OFFICE_IP</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000293640</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292805</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292678</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000291625</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000293964</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292802</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292809</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292804</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000293866</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>SUSP_4</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000293640</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292805</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292678</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000291625</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000293964</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292802</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292809</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000292804</appId>\n" +
                                "<appVersion>1</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                            "<match>\n" +
                                "<appId>0000293866</appId>\n" +
                                "<appVersion>2</appVersion>\n" +
                                "<rule>NEW_RULE7</rule>\n" +
                            "</match>\n" +
                        "</matchResult>\n" +
                        "<ResultMsg>\n" +
                            "<ResultCode>1</ResultCode>\n" +
                            "<ResultMessage>OK</ResultMessage>\n" +
                        "</ResultMsg>\n" +
                    "</CFResponse>\n" +
                "</ConnectorOutput>").toString();
        return ResponseEntity.ok(main_response);

    }

    @GetMapping(value = "/SOAP_TIBCOPODB", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_26_25_SearchPresaleDB_EBPP(HttpServletRequest request){
        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<Resultsets>\n" +
                        "<ResultSet2>\n" +
                            "<Record2>\n" +
                                "<CONTACT_ID>-1</CONTACT_ID>\n" +
                                "<VAR_NUM>1</VAR_NUM>\n" +
                                "<PARAM_NAME>LOAN_PERIOD</PARAM_NAME>\n" +
                                "<PARAM_TYPE>INTEGER</PARAM_TYPE>\n" +
                                "<CHAR_VAL>12</CHAR_VAL>\n" +
                                "<INT_CREATE_DATE>2024-02-29T09:16:07+03:00</INT_CREATE_DATE>\n" +
                            "</Record2>\n" +
                            "<Record2>\n" +
                                "<CONTACT_ID>-1</CONTACT_ID>\n" +
                                "<VAR_NUM>1</VAR_NUM>\n" +
                                "<PARAM_NAME>MKK_APP_ID</PARAM_NAME>\n" +
                                "<PARAM_TYPE>STRING</PARAM_TYPE>\n" +
                                "<CHAR_VAL>2e6be3598deb4c83919cccb015ad603b</CHAR_VAL>\n" +
                                "<INT_CREATE_DATE>2024-02-29T09:16:07+03:00</INT_CREATE_DATE>\n" +
                            "</Record2>\n" +
                        "</ResultSet2>\n" +
                        "<error>0</error>\n" +
                        "<errtext>Ответ от ЕБПП для КРИФ получен</errtext>\n" +
                    "</Resultsets>\n" +
                "</ConnectorOutput>");
    }

    @GetMapping(value = "/Processes/Bussiness/crif_retail_card_new", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DDS_28_05_GetBranchID(HttpServletRequest request){

        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<NumberOfmgnCardResponse>\n" +
                        "<BranchID>0000</BranchID>\n" +
                        "<CoBrand>CRED_CARD_PINSET_GP120</CoBrand>\n" +
                        "<ResultMsg>\n" +
                            "<ResultCode>1</ResultCode>\n" +
                            "<ResultMessage>Success</ResultMessage>\n" +
                        "</ResultMsg>\n" +
                    "</NumberOfmgnCardResponse>\n" +
                "</ConnectorOutput>");

    }

    @GetMapping(value = "/Process/Business/Consumer/GetInterest", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_304_30_RBP_PSK_Reguest(HttpServletRequest request){
        String rq=request.toString();
        String ProcessID=extract(rq,"<ProcessID>","</ProcessID>");
        String ApplicationID=extract(rq,"<ApplicationID>","</ApplicationID>");

        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<InterestRateInquiryResponse>\n" +
                        "<ProcessID>"+ProcessID+"</ProcessID>\n" +
                        "<ProcessName>CRDS</ProcessName>\n" +
                        "<InterestRateResponse>\n" +
                            "<ApplicationID>"+ApplicationID+"</ApplicationID>\n" +
                            "<InterestRate>24.9</InterestRate>\n" +
                        "</InterestRateResponse>\n" +
                        "<ResultMsg>\n" +
                            "<ResultCode>1</ResultCode>\n" +
                            "<ResultMessage>SUCCESS</ResultMessage>\n" +
                        "</ResultMsg>\n" +
                    "</InterestRateInquiryResponse>\n" +
                "</ConnectorOutput>");

    }

    @GetMapping(value = "/mi_so_ABS_Acc_Req", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_36_15_CallAutoABSAcc(HttpServletRequest request){ //здесь же и DS_944_30_CalcPSKInABS
        String rq=request.toString();
        String Application_ID=extract(rq,"<Application_ID>","</Application_ID>");
        String configId=extract(rq,"configId=\"","\" soapAction");

        if (configId.equals("_absAutoAccOpen")) {

            return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<ConnectorOutput>\n" +
                    "<ABS_Accounts_Opening_Response>\n" +
                    "<ProcessID>CREDIT_CARDS</ProcessID>\n" +
                    "<ProcessName>CREDIT_CARDS</ProcessName>\n" +
                    "<ABS_Accounts_Opening_Response>\n" +
                    "<Application_ID>" + Application_ID + "</Application_ID>\n" +
                    "<Request_ID>921271451521</Request_ID>\n" +
                    "<ABSAvailabilityFlag>1</ABSAvailabilityFlag>\n" +
                    "</ABS_Accounts_Opening_Response>\n" +
                    "<ResultMsg>\n" +
                    "<ResultCode>1</ResultCode>\n" +
                    "<ResultMessage>Success</ResultMessage>\n" +
                    "</ResultMsg>\n" +
                    "</ABS_Accounts_Opening_Response>\n" +
                    "</ConnectorOutput>");

        } else {//DS_944_30_CalcPSKInABS

            return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<ConnectorOutput>\n" +
                    "<ABS_Accounts_Opening_Response>\n" +
                    "<ProcessID>CREDIT_CARDS</ProcessID>\n" +
                    "<ProcessName>CREDIT_CARDS</ProcessName>\n" +
                    "<PSKOutput>\n" +
                    "<Request_ID>" + Application_ID + "</Request_ID>\n" +
                    "<Disbursed_Credit_Amount>0</Disbursed_Credit_Amount>\n" +
                    "<PSK_Int>39.860</PSK_Int>\n" +
                    "<PSK_Sum>86818.94</PSK_Sum>\n" +
                    "<Max_PSK>80</Max_PSK>\n" +
                    "<PSK_Excess>0</PSK_Excess>\n" +
                    "</PSKOutput>\n" +
                    "<ResultMsg>\n" +
                    "<ResultCode>1</ResultCode>\n" +
                    "<ResultMessage>Success</ResultMessage>\n" +
                    "</ResultMsg>\n" +
                    "</ABS_Accounts_Opening_Response>\n" +
                    "</ConnectorOutput>");

        }
    }


    @GetMapping(value = "/mi_so_ABS_Acc_Retr", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_36_38_GetCreditInfoAuto(HttpServletRequest request){
        String rq=request.toString();
        String Application_ID=extract(rq,"<Application_ID>","</Application_ID>");
        String Request_ID=extract(rq,"<Request_ID>","</Request_ID>");


        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<ABS_Accounts_Retrieval_Response>\n" +
                        "<ProcessID>CREDIT_CARDS</ProcessID>\n" +
                        "<ProcessName />\n" +
                        "<ABS_Accounts_Retrieval_Response>\n" +
                            "<Application_ID>"+Application_ID+"</Application_ID>\n" +
                            "<Request_ID>"+Request_ID+"</Request_ID>\n" +
                            "<AccountsOutput>\n" +
                                "<Credit_Contract_Number>0013-ND3/02847</Credit_Contract_Number>\n" +
                                "<IBSO_DOG_ID>921271452753</IBSO_DOG_ID>\n" +
                                "<Credit_Contract_date />\n" +
                                "<Credit_Contract_Expiration_Date />\n" +
                                "<Disbursed_Credit_Amount>0</Disbursed_Credit_Amount>\n" +
                                "<Monthly_Repayment_Amount />\n" +
                                "<Repayment_Start_Day />\n" +
                                "<Prior_Repayment_Moratorium_Months />\n" +
                                "<Prior_Repayment_Min_Amount />\n" +
                                "<Prior_Repayment_Fee_pr />\n" +
                                "<Penalty_Fee_Amount />\n" +
                                "<Delinquency_Fee_pr />\n" +
                                "<Interest_Rate_2_pr />\n" +
                                "<Effective_Interest_Rate />\n" +
                                "<Accreditive_Account />\n" +
                                "<Current_Account_Number />\n" +
                                "<Current_Account_Type />\n" +
                                "<Current_Account_Agreement_Number />\n" +
                                "<Current_Account_Agrrement_Date />\n" +
                                "<Loan_Account_Number />\n" +
                                "<Loan_Acc_Opening_Comission_pr />\n" +
                                "<Loan_Acc_Opening_Comission_Max_Amt />\n" +
                                "<Customer_Quality />\n" +
                                "<Loan_Acc_Opening_Commission_Min_Amt />\n" +
                                "<Loan_Acc_Opening_Commission_Calculated />\n" +
                                "<Delinquency_Amount />\n" +
                            "</AccountsOutput>\n" +
                            "<ABSAvailabilityFlag>1</ABSAvailabilityFlag>\n" +
                        "</ABS_Accounts_Retrieval_Response>\n" +
                        "<ResultMsg>\n" +
                            "<ResultCode>1</ResultCode>\n" +
                            "<ResultMessage>SUCCESS - IBSO</ResultMessage>\n" +
                        "</ResultMsg>\n" +
                    "</ABS_Accounts_Retrieval_Response>\n" +
                "</ConnectorOutput>");
    }


    @GetMapping(value = "/mockCreditRegistry__SPARKBinding", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_53_10_CheckBankruptcySPARK(HttpServletRequest request){
        String rq=request.toString();
        String CustomerID=extract(rq,"<CustomerID>","</CustomerID>");

        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<Response>\n" +
                        "<Reports>\n" +
                            "<Report>\n" +
                                "<CustomerID>"+CustomerID+"</CustomerID>\n" +
                                "<ResultMsg>\n" +
                                    "<ResultCode>1</ResultCode>\n" +
                                    "<ResultMessage>OK</ResultMessage>\n" +
                                "</ResultMsg>\n" +
                            "</Report>\n" +
                        "</Reports>\n" +
                    "</Response>\n" +
                "</ConnectorOutput>");


    }

    @GetMapping(value = "/Get_CreditHistory", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_61_15_CallBIAS(HttpServletRequest request){
        String rq=request.toString();
        String CustomerID=extract(rq,"<CustomerID>","</CustomerID>");
        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<Response>\n" +
                        "<Reports>\n" +
                            "<Report>\n" +
                                "<CustomerID>"+CustomerID+"</CustomerID>\n" +
                                "<ResultMsg>\n" +
                                    "<ResultCode>1</ResultCode>\n" +
                                    "<ResultMessage>OK</ResultMessage>\n" +
                                "</ResultMsg>\n" +
                            "</Report>\n" +
                        "</Reports>\n" +
                    "</Response>\n" +
                "</ConnectorOutput>");
    }

    @GetMapping(value = "/Process/Crif_SendCardOper", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_65_10_SendToDS(HttpServletRequest request){
        return ResponseEntity.ok().build();//нет примеров запросов и ответов
    }

    @GetMapping(value = "/Send_SMS_Service", produces = "multipart/form-data;charset=UTF-8")
    public ResponseEntity DS_906_10_SendSMS(HttpServletRequest request){
        return ResponseEntity.ok("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ConnectorOutput>\n" +
                    "<ResultMsg ResultCode=\"0\" ResultMsg=\"OK\" />\n" +
                "</ConnectorOutput>");
    }


}


