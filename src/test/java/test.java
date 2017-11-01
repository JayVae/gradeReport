import com.grade.CreateWord;
import com.grade.QueryData;
import com.grade.excalSheet;
import freemarker.template.TemplateException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/2/27.
 */
public class test {
    public static void main(String[] args) throws InvalidFormatException, TemplateException, IOException {
        QueryData queryData = new QueryData();
        excalSheet sheet = new excalSheet();
        System.out.println(queryData.getAverageGrade(sheet.getAnsResult()));

    }
}
