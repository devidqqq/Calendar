import java.util.Calendar;
import java.util.Scanner;

/**
 * 日期类的综合运用，控制台输出日历
 */
public class CalendarDemo {

	public static int weekDay(Calendar cal){

		int weekDay = cal.get(Calendar.DAY_OF_WEEK);
		if (weekDay == 1)// 日历从星期天开始
			weekDay = 7;
		else
			weekDay -= 1;
		return weekDay;
	}

	/**
	 * 显示一个月的日历
	 * @param year
	 * @param number
	 * @param displayType
	 */
	public void calendar(int year, int number, int displayType) {
		Calendar firstCal = Calendar.getInstance();
		Calendar lastCal = Calendar.getInstance();
		int month ;
		for (int i = 1 ;i<=12 ;i++){
			month = i;
			System.out.println("\t\t" + "   " + month + "月"+"   "+"\t\t");
			System.out.print("日\t一\t二\t三\t四\t五\t六");
			System.out.println();
			dateOfMonth(year, month, firstCal, lastCal);
		}

	}

	/**
	 * 显示每个月具体内容
	 * @param year
	 * @param month
	 * @param firstCal
	 * @param lastCal
	 */
	private void dateOfMonth(int year, int month,Calendar firstCal,Calendar lastCal){
		firstCal.set(year, month - 1, 1);// 所求月的第一天
		int dateOfMonth = firstCal.getActualMaximum(Calendar.DATE);// 获取该月的天数
		lastCal.set(year, month - 1, dateOfMonth);// 所求月的最后一天

		int weekOfMonth = firstCal.getActualMaximum(Calendar.WEEK_OF_MONTH); // 获取该月的星期数
		String[][] week = new String[weekOfMonth][7]; // 将一个月按星期统计
		int firstDay = CalendarDemo.weekDay(firstCal); // 获取所求月第一天是星期几
		int lastDay = CalendarDemo.weekDay(lastCal); // 获取所求月最后一天是星期几
		int m = 1; // 用于日历中显示天数
		int f = 1;// f的作用主要是判断是否需要将第一个星期归到中间几个星期一起计算
		int j;
		// 第一个星期
		if (firstDay == 7){
			f = 0;
		}else {
			for (j = 0; j < firstDay; j++) //每月前几天补空格
			{
				week[0][j] = " ";
				System.out.print(week[0][j] + "\t");
			}
			for (j = firstDay; j < 7; j++)
			{
				week[0][j] = m + "";
				m++;
				System.out.print(week[0][j] + "\t");
			}
			System.out.println();
		}
		// 中间的几个星期
		for (int i = f; i < weekOfMonth - 1; i++){
			for (j = 0; j < 7; j++)
			{
				week[i][j] = m + "";
				m++;
				System.out.print(week[i][j] + "\t");
			}
			System.out.println();
		}
		// 最后一个星期
		if (lastDay == 7){
			week[weekOfMonth - 1][0] = m + "";
			System.out.print(week[weekOfMonth - 1][0] + "\t");
		}else {
			for (j = 0; j <= lastDay; j++) {
				week[weekOfMonth - 1][j] = m + "";
				m++;
				System.out.print(week[weekOfMonth - 1][j] + "\t");
			}
		}
		System.out.println();
	}


	public static void main(String[] args)
	{
		CalendarDemo myCal = new CalendarDemo();
		Scanner input = new Scanner(System.in);
		System.out.println("<<<<<<<<<<<<<<万年历>>>>>>>>>>>>>>");
		System.out.print("请输入年份：");
		int year = input.nextInt();
		System.out.print("请输入每行显示的月份个数：");
		int number = input.nextInt();
		System.out.print("请确定按横行显示还是竖行显示，横行显示输入“1”，竖行显示输入“2”：");
		int displayType = input.nextInt();
		while(!(displayType==1 || displayType==2)){
			System.out.print("显示方式输入错误，请重新输入：");
			number = input.nextInt();
		}
		myCal.calendar(year,number,displayType);
	}
}
