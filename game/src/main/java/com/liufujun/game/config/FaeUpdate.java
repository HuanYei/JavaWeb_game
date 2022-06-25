package com.liufujun.game.config;

import com.liufujun.game.util.Fileprocessing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FaeUpdate {

    public static void autoUpdate() {
        File directory = new File("");//参数为空
        String courseFile="";
        try {
             courseFile = directory.getCanonicalPath() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        downloadByNIO2("http://172.168.1.230:8888/Versiondownload", courseFile, "fae_tool_update.log");
        String Ver=courseFile+"\\fae_tool_update.log";
        int fwqvercode=Integer.parseInt(Fileprocessing.findJBFile(Ver,"VersionCode="));
        String e更新内容=Fileprocessing.readTxtFile(Ver).split("\n")[2];
        System.out.println(fwqvercode);
        updatecofig();
        if (fwqvercode>Versionconfig.VerCode){
            downloadByNIO2("http://172.168.1.230:8888/autodownload", courseFile, "jar.jar");
            System.out.println("done...");
            try {
                Runtime.getRuntime().exec("mshta vbscript:msgbox(\""+e更新内容+"\",64,\"FAE工具更新内容\")(window.close)");
                String cmd="\""+courseFile+ "\\update.bat \"";
                System.out.println(cmd);
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void updatecofig() {
        String list[]=Fileprocessing.readTxtFile("res/config/国家.config").split("\n");
        boolean  is国家更新=true;
        for (int i = 0; i <list.length ; i++) {
            if (list[i].indexOf("=ARE=")!=-1){
                list[i]=list[i].replace("=ARE=","=_ARE_=");
            }
            if (list[i].indexOf("=Peru=")!=-1){
                list[i]=list[i].replace("=Peru=","=_Peru_=");
            }
            if (list[i].indexOf("圣文森特和格林纳丁斯=a=VG=Saint_Vincent_and_the_Grenadines")!=-1){
                is国家更新=false;
            }
        }
        String b="";
        for (String a:
             list) {
            b+=a+"\n";
        }
        String  c="\n阿富汗=a=AF=Afghanistan=a=a=a=a=a\n" +
                "奥兰群岛=a=AX=Aland_Islands=a=a=a=a=a\n" +
                "阿尔巴尼亚=a=AL=Albania=a=a=a=a=a\n" +
                "阿尔及利亚=a=DZ=Algeria=a=a=a=a=a\n" +
                "美属萨摩亚=a=AS=American_Samoa=a=a=a=a=a\n" +
                "安道尔=a=AD=Andorra=a=a=a=a=a\n" +
                "安哥拉=a=AO=Angola=a=a=a=a=a\n" +
                "安圭拉=a=AI=Anguilla=a=a=a=a=a\n" +
                "安提瓜和巴布达=a=AG=Antigua_and_Barbuda=a=a=a=a=a\n" +
                "阿根廷=a=AR=Argentina=a=a=a=a=a\n" +
                "亚美尼亚=a=AM=Armenia=a=a=a=a=a\n" +
                "阿鲁巴=a=AW=Aruba=a=a=a=a=a\n" +
                "澳大利亚=a=AU=Australia=a=a=a=a=a\n" +
                "奥地利=a=AT=Austria=a=a=a=a=a\n" +
                "阿塞拜疆=a=AZ=Azerbaijan=a=a=a=a=a\n" +
                "孟加拉=a=BD=Bangladesh=a=a=a=a=a\n" +
                "巴林=a=BH=Bahrain=a=a=a=a=a\n" +
                "巴哈马=a=BS=Bahamas=a=a=a=a=a\n" +
                "巴巴多斯=a=BB=Barbados=a=a=a=a=a\n" +
                "白俄罗斯=a=BY=Belarus=a=a=a=a=a\n" +
                "比利时=a=BE=Belgium=a=a=a=a=a\n" +
                "伯利兹=a=BZ=Belize=a=a=a=a=a\n" +
                "贝宁=a=BJ=Benin=a=a=a=a=a\n" +
                "百慕大=a=BM=Bermuda=a=a=a=a=a\n" +
                "不丹=a=BT=Bhutan=a=a=a=a=a\n" +
                "玻利维亚=a=BO=Bolivia=a=a=a=a=a\n" +
                "波斯尼亚和黑塞哥维那=a=BA=Bosnia_and_Herzegovina=a=a=a=a=a\n" +
                "博茨瓦纳=a=BW=Botswana=a=a=a=a=a\n" +
                "布维岛=a=BV=Bouvet_Island=a=a=a=a=a\n" +
                "巴西=a=BR=Brazil=a=a=a=a=a\n" +
                "文莱=a=BN=Brunei=a=a=a=a=a\n" +
                "保加利亚=a=BG=Bulgaria=a=a=a=a=a\n" +
                "布基纳法索=a=BF=Burkina_Faso=a=a=a=a=a\n" +
                "布隆迪=a=BI=Burundi=a=a=a=a=a\n" +
                "柬埔寨=a=KH=Cambodia=a=a=a=a=a\n" +
                "喀麦隆=a=CM=Cameroon=a=a=a=a=a\n" +
                "加拿大=a=CA=Canada=a=a=a=a=a\n" +
                "佛得角=a=CV=Cape_Verde=a=a=a=a=a\n" +
                "中非=a=CF=Central_African_Republic=a=a=a=a=a\n" +
                "乍得=a=TD=Chad=a=a=a=a=a\n" +
                "智利=a=CL=Chile=a=a=a=a=a\n" +
                "圣诞岛=a=CX=Christmas_Islands=a=a=a=a=a\n" +
                "科科斯（基林）群岛=a=CC=Cocos_keeling_Islands=a=a=a=a=a\n" +
                "哥伦比亚=a=CO=Colombia=a=a=a=a=a\n" +
                "科摩罗=a=KM=Comoros=a=a=a=a=a\n" +
                "刚果（金）=a=CD=Congo_Congo_Kinshasa=a=a=a=a=a\n" +
                "刚果=a=CG=Congo=a=a=a=a=a\n" +
                "库克群岛=a=CK=Cook_Islands=a=a=a=a=a\n" +
                "哥斯达黎加=a=CR=Costa_Rica=a=a=a=a=a\n" +
                "科特迪瓦=a=CI=Cote_D'Ivoire=a=a=a=a=a\n" +
                "中国=a=CN=China=a=a=a=a=a\n" +
                "克罗地亚=a=HR=Croatia=a=a=a=a=a\n" +
                "古巴=a=CU=Cuba=a=a=a=a=a\n" +
                "捷克=a=CZ=Czech=a=a=a=a=a\n" +
                "塞浦路斯=a=CY=Cyprus=a=a=a=a=a\n" +
                "丹麦=a=DK=Denmark=a=a=a=a=a\n" +
                "吉布提=a=DJ=Djibouti=a=a=a=a=a\n" +
                "多米尼加=a=DM=Dominica=a=a=a=a=a\n" +
                "东帝汶=a=a=East_Timor=a=a=a=a=a\n" +
                "厄瓜多尔=a=EC=Ecuador=a=a=a=a=a\n" +
                "埃及=a=EG=Egypt=a=a=a=a=a\n" +
                "赤道几内亚=a=GQ=Equatorial_Guinea=a=a=a=a=a\n" +
                "厄立特里亚=a=ER=Eritrea=a=a=a=a=a\n" +
                "爱沙尼亚=a=EE=Estonia=a=a=a=a=a\n" +
                "埃塞俄比亚=a=ET=Ethiopia=a=a=a=a=a\n" +
                "法罗群岛=a=FO=Faroe_Islands=a=a=a=a=a\n" +
                "斐济=a=FJ=Fiji=a=a=a=a=a\n" +
                "芬兰=a=FI=Finland=a=a=a=a=a\n" +
                "法国=a=FR=France=a=a=a=a=a\n" +
                "法国大都会=a=FX=Franch_Metropolitan=a=a=a=a=a\n" +
                "法属圭亚那=a=GF=Franch_Guiana=a=a=a=a=a\n" +
                "法属波利尼西亚=a=PF=French_Polynesia=a=a=a=a=a\n" +
                "加蓬=a=GA=Gabon=a=a=a=a=a\n" +
                "冈比亚=a=GM=Gambia=a=a=a=a=a\n" +
                "格鲁吉亚=a=GE=Georgia=a=a=a=a=a\n" +
                "德国=a=DE=Germany=a=a=a=a=a\n" +
                "加纳=a=GH=Ghana=a=a=a=a=a\n" +
                "直布罗陀=a=GI=Gibraltar=a=a=a=a=a\n" +
                "希腊=a=GR=Greece=a=a=a=a=a\n" +
                "格林纳达=a=GD=Grenada=a=a=a=a=a\n" +
                "瓜德罗普岛=a=GP=Guadeloupe=a=a=a=a=a\n" +
                "关岛=a=GU=Guam=a=a=a=a=a\n" +
                "危地马拉=a=GT=Guatemala=a=a=a=a=a\n" +
                "根西岛=a=GG=Guernsey=a=a=a=a=a\n" +
                "几内亚比绍=a=GW=Guinea_Bissau=a=a=a=a=a\n" +
                "几内亚=a=GN=Guinea=a=a=a=a=a\n" +
                "圭亚那=a=GY=Guyana=a=a=a=a=a\n" +
                "香港_（中国）=a=HK=Hong_Kong=a=a=a=a=a\n" +
                "海地=a=HT=Haiti=a=a=a=a=a\n" +
                "洪都拉斯=a=HN=Honduras=a=a=a=a=a\n" +
                "匈牙利=a=HU=Hungary=a=a=a=a=a\n" +
                "冰岛=a=IS=Iceland=a=a=a=a=a\n" +
                "印度=a=IN=India=a=a=a=a=a\n" +
                "印度尼西亚=a=ID=Indonesia=a=a=a=a=a\n" +
                "伊朗=a=IR=Iran=a=a=a=a=a\n" +
                "伊拉克=a=IQ=Iraq=a=a=a=a=a\n" +
                "爱尔兰=a=IE=Ireland=a=a=a=a=a\n" +
                "马恩岛=a=IM=Isle_of_Man=a=a=a=a=a\n" +
                "以色列=a=IL=Israel=a=a=a=a=a\n" +
                "意大利=a=IT=Italy=a=a=a=a=a\n" +
                "牙买加=a=JM=Jamaica=a=a=a=a=a\n" +
                "日本=a=JP=Japan=a=a=a=a=a\n" +
                "泽西岛=a=a=Jersey=a=a=a=a=a\n" +
                "约旦=a=JO=Jordan=a=a=a=a=a\n" +
                "哈萨克斯坦=a=KZ=Kazakstan=a=a=a=a=a\n" +
                "肯尼亚=a=KE=Kenya=a=a=a=a=a\n" +
                "基里巴斯=a=KI=Kiribati=a=a=a=a=a\n" +
                "韩国=a=KR=Korea_South=a=a=a=a=a\n" +
                "朝鲜=a=KD=Korea_North=a=a=a=a=a\n" +
                "科威特=a=KW=Kuwait=a=a=a=a=a\n" +
                "吉尔吉斯斯坦=a=KG=Kyrgyzstan=a=a=a=a=a\n" +
                "老挝=a=LO=Laos=a=a=a=a=a\n" +
                "拉脱维亚=a=LV=Latvia=a=a=a=a=a\n" +
                "黎巴嫩=a=LB=Lebanon=a=a=a=a=a\n" +
                "莱索托=a=LS=Lesotho=a=a=a=a=a\n" +
                "利比里亚=a=LR=Liberia=a=a=a=a=a\n" +
                "利比亚=a=LY=Libya=a=a=a=a=a\n" +
                "列支敦士登=a=LI=Liechtenstein=a=a=a=a=a\n" +
                "立陶宛=a=LT=Lithuania=a=a=a=a=a\n" +
                "卢森堡=a=LU=Luxembourg=a=a=a=a=a\n" +
                "澳门（中国）=a=MO=Macau=a=a=a=a=a\n" +
                "马其顿=a=MK=Macedonia=a=a=a=a=a\n" +
                "马拉维=a=MW=Malawi=a=a=a=a=a\n" +
                "马来西亚=a=MY=Malaysia=a=a=a=a=a\n" +
                "马达加斯加=a=MG=Madagascar=a=a=a=a=a\n" +
                "马尔代夫=a=MV=Maldives=a=a=a=a=a\n" +
                "马里=a=ML=Mali=a=a=a=a=a\n" +
                "马耳他=a=MT=Malta=a=a=a=a=a\n" +
                "马绍尔群岛=a=MH=Marshall_Islands=a=a=a=a=a\n" +
                "马提尼克岛=a=MQ=Martinique=a=a=a=a=a\n" +
                "毛里塔尼亚=a=MR=Mauritania=a=a=a=a=a\n" +
                "毛里求斯=a=MU=Mauritius=a=a=a=a=a\n" +
                "马约特=a=YT=Mayotte=a=a=a=a=a\n" +
                "墨西哥=a=MX=Mexico=a=a=a=a=a\n" +
                "密克罗尼西亚=a=MF=Micronesia=a=a=a=a=a\n" +
                "摩尔多瓦=a=MD=Moldova=a=a=a=a=a\n" +
                "摩纳哥=a=MC=Monaco=a=a=a=a=a\n" +
                "蒙古=a=MN=Mongolia=a=a=a=a=a\n" +
                "黑山=a=ME=Montenegro=a=a=a=a=a\n" +
                "蒙特塞拉特=a=MS=Montserrat=a=a=a=a=a\n" +
                "摩洛哥=a=MA=Morocco=a=a=a=a=a\n" +
                "莫桑比克=a=MZ=Mozambique=a=a=a=a=a\n" +
                "缅甸=a=MM=Myanmar=a=a=a=a=a\n" +
                "纳米比亚=a=NA=Namibia=a=a=a=a=a\n" +
                "瑙鲁=a=NR=Nauru=a=a=a=a=a\n" +
                "尼泊尔=a=NP=Nepal=a=a=a=a=a\n" +
                "荷兰=a=NL=Netherlands=a=a=a=a=a\n" +
                "新喀里多尼亚=a=NC=New_Caledonia=a=a=a=a=a\n" +
                "新西兰=a=NZ=New_Zealand=a=a=a=a=a\n" +
                "尼加拉瓜=a=NI=Nicaragua=a=a=a=a=a\n" +
                "尼日尔=a=NE=Niger=a=a=a=a=a\n" +
                "尼日利亚=a=NG=Nigeria=a=a=a=a=a\n" +
                "纽埃=a=NU=Niue=a=a=a=a=a\n" +
                "诺福克岛=a=NF=Norfolk_Island=a=a=a=a=a\n" +
                "挪威=a=NO=Norway=a=a=a=a=a\n" +
                "阿曼=a=OM=Oman=a=a=a=a=a\n" +
                "巴基斯坦=a=PK=Pakistan=a=a=a=a=a\n" +
                "帕劳=a=PW=Palau=a=a=a=a=a\n" +
                "巴勒斯坦=a=PS=Palestine=a=a=a=a=a\n" +
                "巴拿马=a=PA=Panama=a=a=a=a=a\n" +
                "巴布亚新几内亚=a=PG=Papua_New_Guinea=a=a=a=a=a\n" +
                "巴拉圭=a=PY=Paraguay=a=a=a=a=a\n" +
                "秘鲁=a=PE=Peru=a=a=a=a=a\n" +
                "菲律宾=a=PH=Philippines=a=a=a=a=a\n" +
                "皮特凯恩群岛=a=PN=Pitcairn_Islands=a=a=a=a=a\n" +
                "波兰=a=PL=Poland=a=a=a=a=a\n" +
                "葡萄牙=a=PT=Portugal=a=a=a=a=a\n" +
                "波多黎各=a=PR=Puerto_Rico=a=a=a=a=a\n" +
                "卡塔尔=a=QA=Qatar=a=a=a=a=a\n" +
                "留尼汪岛=a=RE=Reunion=a=a=a=a=a\n" +
                "罗马尼亚=a=RO=Romania=a=a=a=a=a\n" +
                "卢旺达=a=RW=Rwanda=a=a=a=a=a\n" +
                "俄罗斯联邦=a=RU=Russian_Federation=a=a=a=a=a\n" +
                "圣赫勒拿=a=SH=Saint_Helena=a=a=a=a=a\n" +
                "圣基茨和尼维斯=a=KN=Saint_Kitts_Nevis=a=a=a=a=a\n" +
                "圣卢西亚=a=LC=Saint_Lucia=a=a=a=a=a\n" +
                "圣文森特和格林纳丁斯=a=VG=Saint_Vincent_and_the_Grenadines=a=a=a=a=a\n" +
                "萨尔瓦多=a=SV=El_Salvador=a=a=a=a=a\n" +
                "萨摩亚=a=WS=Samoa=a=a=a=a=a\n" +
                "圣马力诺=a=SM=San_Marino=a=a=a=a=a\n" +
                "圣多美和普林西比=a=ST=Sao_Tome_and_Principe=a=a=a=a=a\n" +
                "沙特阿拉伯=a=SA=Saudi_Arabia=a=a=a=a=a\n" +
                "塞内加尔=a=SN=Senegal=a=a=a=a=a\n" +
                "塞舌尔=a=SC=Seychelles=a=a=a=a=a\n" +
                "塞拉利昂=a=SL=Sierra_Leone=a=a=a=a=a\n" +
                "新加坡=a=SG=Singapore=a=a=a=a=a\n" +
                "塞尔维亚=a=RS=Serbia=a=a=a=a=a\n" +
                "斯洛伐克=a=SK=Slovakia=a=a=a=a=a\n" +
                "斯洛文尼亚=a=SI=Slovenia=a=a=a=a=a\n" +
                "所罗门群岛=a=SB=Solomon_Islands=a=a=a=a=a\n" +
                "索马里=a=SO=Somalia=a=a=a=a=a\n" +
                "南非=a=ZA=South_Africa=a=a=a=a=a\n" +
                "西班牙=a=ES=Spain=a=a=a=a=a\n" +
                "斯里兰卡=a=LK=Sri_Lanka=a=a=a=a=a\n" +
                "苏丹=a=SD=Sudan=a=a=a=a=a\n" +
                "苏里南=a=SR=Suriname=a=a=a=a=a\n" +
                "斯威士兰=a=SZ=Swaziland=a=a=a=a=a\n" +
                "瑞典=a=SE=Sweden=a=a=a=a=a\n" +
                "瑞士=a=CH=Switzerland=a=a=a=a=a\n" +
                "叙利亚=a=SY=Syria=a=a=a=a=a\n" +
                "塔吉克斯坦=a=TJ=Tajikistan=a=a=a=a=a\n" +
                "坦桑尼亚=a=a=Tanzania=a=a=a=a=a\n" +
                "台湾_（中国）=a=TW=Taiwan=a=a=a=a=a\n" +
                "泰国=a=TH=Thailand=a=a=a=a=a\n" +
                "特立尼达和多巴哥=a=TT=Trinidad_and_Tobago=a=a=a=a=a\n" +
                "东帝汶=a=TL=Timor_Leste=a=a=a=a=a\n" +
                "多哥=a=TG=Togo=a=a=a=a=a\n" +
                "托克劳=a=TK=Tokelau=a=a=a=a=a\n" +
                "汤加=a=TO=Tonga=a=a=a=a=a\n" +
                "突尼斯=a=TN=Tunisia=a=a=a=a=a\n" +
                "土耳其=a=TR=Turkey=a=a=a=a=a\n" +
                "土库曼斯坦=a=TM=Turkmenistan=a=a=a=a=a\n" +
                "图瓦卢=a=TV=Tuvalu=a=a=a=a=a\n" +
                "乌干达=a=UG=Uganda=a=a=a=a=a\n" +
                "乌克兰=a=UA=Ukraine=a=a=a=a=a\n" +
                "阿拉伯联合酋长国=a=AE=United_Arab_Emirates=a=a=a=a=a\n" +
                "英国=a=UK=United_Kingdom=a=a=a=a=a\n" +
                "美国=a=US=United_States=a=a=a=a=a\n" +
                "乌拉圭=a=UY=Uruguay=a=a=a=a=a\n" +
                "乌兹别克斯坦=a=UZ=Uzbekistan=a=a=a=a=a\n" +
                "瓦努阿图=a=VN=Vanuatu=a=a=a=a=a\n" +
                "梵蒂冈=a=a=Vatican_City=a=a=a=a=a\n" +
                "委内瑞拉=a=VE=Venezuela=a=a=a=a=a\n" +
                "越南=a=VN=Vietnam=a=a=a=a=a\n" +
                "瓦利斯群岛和富图纳群岛=a=WF=Wallis_and_Futuna=a=a=a=a=a\n" +
                "西撒哈拉=a=EH=Western_Sahara=a=a=a=a=a\n" +
                "也门=a=YE=Yemen=a=a=a=a=a\n" +
                "南斯拉夫=a=a=Yugoslavia=a=a=a=a=a\n" +
                "赞比亚=a=ZM=Zambia=a=a=a=a=a\n" +
                "津巴布韦=a=ZW=Zimbabwe=a=a=a=a=a";
        if (is国家更新){
            Fileprocessing.updateFile("res/config/国家.config",b+c);
        }else {
            Fileprocessing.updateFile("res/config/国家.config",b);
        }

    }


    public static void downloadByNIO2(String url, String saveDir, String fileName) {
        try (InputStream ins = new URL(url).openStream()) {
            Path target = Paths.get(saveDir, fileName);
            Files.createDirectories(target.getParent());
            Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
