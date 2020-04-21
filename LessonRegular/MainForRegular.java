package LessonRegular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainForRegular {
    public void isEmail(String email) {
        if (email == null)
            email = "kkk@maps.yandex.ru";
        Pattern pattern = Pattern.compile("^[a-z\\.\\-A-Z0-9_-]+@[a-z\\.\\-A-Z0-9_^.]+[\\.][a-zA-Z]{2,}$");
        Pattern pDomainF = Pattern.compile("@[a-z\\-A-Z0-9]+[\\.]?");
        Pattern pDomainL = Pattern.compile("[\\.]+[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            System.out.println(email.substring(matcher.start(), matcher.end()));
            matcher = pDomainF.matcher(email);
            if (matcher.find())
                System.out.println(email.substring(matcher.start() + 1, matcher.end() - 1));
            matcher = pDomainL.matcher(email);
            if (matcher.find())
                System.out.println(email.substring(matcher.start() + 1, matcher.end()));
        }
        else {
            System.out.print("It is not email");
        }
    }
    public void getDomains (String text) {
        if (text == null)
            text = "bilbidar fhgua \nfyegs.com\n https://jhfatsy.net jfahd nsadg www.dywjd-fsa.me. The next time futcjs dsb dnsdjfy hwqndm dkj.nfwh.cje, sfasdh nfhasf.cj.gehy.kdsa";
        Pattern pattern = Pattern.compile("[a-z\\.\\-A-Z0-9]+[\\.][a-zA-Z]{2,}");
        Matcher domains = pattern.matcher(text);
        while (domains.find()) {
            System.out.println(text.substring(domains.start(), domains.end()));
        }
    }
}

