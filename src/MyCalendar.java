import java.util.*;

public class MyCalendar {


	/**
	 * 重置每周的天数，使之与日历对应
	 * @param cal
	 */
	private int resetWeekDay(Calendar cal){
		int weekDay = cal.get(Calendar.DAY_OF_WEEK); // 获取一周中的第几天
		if(weekDay == 1){
			weekDay = 7;
		}else{
			weekDay -= 1;
		}
		return weekDay;
	}

	/**
	 * 输出第一行
	 * @param firstDayOfWeek
	 * @param m
	 * @return
	 */
	private int writeFirstLine(int firstDayOfWeek, int m){

		if(firstDayOfWeek==7){
			for (int i = 0; i<7; i++){
				m += 1;
				System.out.print(m+"\t");
			}
			System.out.print("\t");
		}else{
			for(int i = 0; i<firstDayOfWeek; i++){
				System.out.print(" "+"\t");
			}
			for (int i = firstDayOfWeek; i<7; i++){
				m += 1;
				System.out.print(m+"\t");
			}
			System.out.print("\t");
		}
		return m;
	}

	/**
	 * 输出其他行
	 * @param year
	 * @param map
	 * @return
	 */
	private Map writeOtherLines(int year, Map<Integer,Integer> map){
		Set<Integer> set = map.keySet();
		for(Integer month:set){
			int currentDay = map.get(month);
			int maxDay = genMaxDay(year, month);
			int l = currentDay;
			if(currentDay+7<=maxDay){
				for (int i = l; i<l+7; i++){
					currentDay += 1;
					System.out.print(currentDay+"\t");
				}

			}else{
				for (int i = l; i<maxDay;i++){
					currentDay += 1;
					System.out.print(currentDay+"\t");
				}
				for (int i = maxDay; i<l+7;i++){
					System.out.print(" "+"\t");
				}
			}
			map.put(month,currentDay);
			System.out.print("\t");
		}
		System.out.println();
		return map;
	}


	/**
	 * 获取当前月的第一天是星期几
	 * @param year
	 * @param month
	 * @return
	 */
	public int genFirstDay(int year, int month){
		Calendar firstDay = Calendar.getInstance(); // 实例化一个月中第一天
		firstDay.set(year,month-1,1); // 设置firstMonth为本月第一天
		int firstDayOfWeek = resetWeekDay(firstDay); // 获取本月第一天是星期几
		return firstDayOfWeek;
	}


	/**
	 * 获取每月有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public int genMaxDay(int year,int month){
		Calendar CurrentMonth = Calendar.getInstance(); // 实例化一个月中
		CurrentMonth.set(Calendar.YEAR,year); // 设置firstMonth为本月
		CurrentMonth.set(Calendar.MONTH,month-1);// 设置firstMonth为本月
		int maxDay = CurrentMonth.getActualMaximum(Calendar.DAY_OF_MONTH); // 获取本月有多少天
		return maxDay;
	}

	/**
	 * 输出日历表头
	 * @param month
	 * @param number
	 */
	public void writeTitle(int month, int number){
		for(int n = 1 ;n<=number ;n++) { // 写月份
			month += 1;
			System.out.print("\t\t" + "   " + month + "月"+"   "+"\t\t\t\t");
		}
		System.out.println();
		for(int n = 1 ;n<=number ;n++) { // 星期
			System.out.print("日\t一\t二\t三\t四\t五\t六\t\t");
		}
		System.out.println();
	}

	/**
	 * 向控制台输出日历
	 * @param month
	 * @param number
	 * @param year
	 * @return
	 */
	public int write(int month,int number,int year){
		Map<Integer,Integer> map = new HashMap<>();
		writeTitle( month, number);
		//输出第一行
		for (int j = 0;j<number;j++){
			month+=1;
			int firstDayOfWeek = genFirstDay( year, month);
			map.put(month,writeFirstLine(firstDayOfWeek, 0));
		}
		System.out.println();
		//输出中间行
		for (int k = 0; k<6; k++){
			map = writeOtherLines(year, map);
		}
		return month;
	}

	public static void main(String[] args) {
		MyCalendar myCalendar = new MyCalendar();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入年份：");
		int year = sc.nextInt();
		System.out.println("请输入每行显示的月份个数：");
		int number = sc.nextInt();
		int line = 12/number;
		int lastLine = 12%number;
		int month = 0;
		for (int i = 0;i<line;i++){
			month = myCalendar.write( month,number, year);
		}
		if (lastLine!=0){
			myCalendar.write( month,lastLine, year);
		}

	}
}
