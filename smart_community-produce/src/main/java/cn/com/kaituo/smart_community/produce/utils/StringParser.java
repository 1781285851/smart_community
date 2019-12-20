package cn.com.kaituo.smart_community.produce.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
	private static final char UNDERLINE = '_';

	/**
	 * 转为数据库代码，全小写连词符格式
	 * @param str
	 * @return
	 */
	public static String converterDatabaseCode(String str){
		String regex =".*[A-Z].*";
		if(!hasUnderline(str) &&str.matches(regex))
			str =camelToUnderline(str);
		return StringUtils.lowerCase(str);
	}

	/**
	 * 数据库代码转为类名，首字母大写的驼峰格式
	 * @param str
	 * @return
	 */
	public static String converterClassName(String str){
		if(hasUnderline(str))
			str =underlineToFirstUpperCamel(str);
		else if(StringUtils.isNotBlank(str) &&Character.isLowerCase(str.charAt(0)))
			str =upperFirstLatter(str);

		return str;
	}

	/**
	 * 数据库代码转为属性名，首字母小写的驼峰格式
	 * @param str
	 * @return
	 */
	public static String converterFieldName(String str){
		if(hasUnderline(str))
			str =underlineToCamel(str);

		return str;
	}

	public static boolean hasUnderline(String param){
		if (StringUtils.isNotBlank(param) &&param.contains(String.valueOf(UNDERLINE))) {
			return true;
		}
		return false;
	}

	/**
	 * 首字母转为大写
	 * @param str
	 * @return
	 */
	public static String upperFirstLatter(String str){
		if(StringUtils.isNotBlank(str))
			return str.substring(0, 1).toUpperCase()+str.substring(1);
		return str;
	}

	/**
	 * 驼峰格式字符串转换为下划线格式字符串
	 *
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (StringUtils.isBlank(param)) {
			return param;
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		boolean takeLowerCase =false;
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				if(takeLowerCase)
					sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
				takeLowerCase =false;
			} else {
				sb.append(c);
				takeLowerCase =true;
			}
		}
		return sb.toString();
	}


	/**
	 * 下划线格式字符串转换为首字母大写的驼峰格式字符串
	 * （一般用于生成类名）
	 * @param param
	 * @return
	 */
	public static String underlineToFirstUpperCamel(String param) {
		return upperFirstLatter(underlineToCamel(param));
	}

	/**
	 * 下划线格式字符串转换为驼峰格式字符串
	 *
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if(StringUtils.isBlank(param) ||!hasUnderline(param))		//字串为空或其中不包含下划线，返回原字符串
			return param;
		param =param.toLowerCase();		//全部转换为小写
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String test = "_app___version_fld_dbms";
		System.out.println(test.matches(".*[A-Z].*") +converterDatabaseCode(test));
		test = "_app___Version_fld_DBMS";
		System.out.println(test.matches(".*[A-Z].*") +converterDatabaseCode(test));
		test = "AppVersionFldDBMS";
		System.out.println(test.matches(".*[A-Z].*") +converterDatabaseCode(test));
	}
}