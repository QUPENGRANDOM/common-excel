package pengq.common.excel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonExcelStarterApplicationTests {

	@Test
	public void contextLoads() {
		long startTime = System.currentTimeMillis();

		ExcelReader reader = null;
		try {
			reader = new ExcelReader("D:\\logs\\黑龙江省红星合作店明细清单.xlsx");
			List<Common> list = reader.read(Common.class);
			System.out.println("end : " + (System.currentTimeMillis() - startTime));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (reader != null){
				reader.closeWorkbook();
			}
		}
	}
}
