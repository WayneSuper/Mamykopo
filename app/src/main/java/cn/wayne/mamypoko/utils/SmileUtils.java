package cn.wayne.mamypoko.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.text.style.ImageSpan;

import cn.wayne.mamypoko.R;


public class SmileUtils {
	public static final String smiley_0 = "[):]";
	public static final String smiley_1 = "[:D]";
	public static final String smiley_2 = "[;)]";
	public static final String smiley_3 = "[:-o]";
	public static final String smiley_4 = "[:p]";
	public static final String smiley_5 = "[(H)]";
	public static final String smiley_6 = "[:@]";
	public static final String smiley_7 = "[:s]";
	public static final String smiley_8 = "[:$]";
	public static final String smiley_9 = "[:(]";
	public static final String smiley_10 = "[:'(]";
	public static final String smiley_11 = "[:|]";
	public static final String smiley_12 = "[(a)]";
	public static final String smiley_13 = "[8o|]";
	public static final String smiley_14 = "[8-|]";
	public static final String smiley_15 = "[+o(]";
	public static final String smiley_16 = "[<o)]";
	public static final String smiley_17 = "[|-)]";
	public static final String smiley_18 = "[*-)]";
	public static final String smiley_19 = "[:-#]";
	public static final String smiley_20 = "[:-*]";
	public static final String smiley_21 = "[^o)]";
	public static final String smiley_22 = "[8-)]";
	public static final String smiley_23 = "[(|)]";
	public static final String smiley_24 = "[(u)]";
	public static final String smiley_25 = "[(S)]";
	public static final String smiley_26 = "[(*)]";
	public static final String smiley_27 = "[(#)]";
	public static final String smiley_28 = "[(R)]";
	public static final String smiley_29 = "[({)]";
	public static final String smiley_30 = "[(})]";
	public static final String smiley_31 = "[(k)]";
	public static final String smiley_32 = "[(F)]";
	public static final String smiley_33 = "[(W)]";
	public static final String smiley_34 = "[(C)]";
	public static final String smiley_35 = "[(a)]";
	public static final String smiley_36 = "[(b)]";
	public static final String smiley_37 = "[(c)]";
	public static final String smiley_38 = "[(d)]";
	public static final String smiley_39 = "[(e)]";
	public static final String smiley_40 = "[(f)]";
	public static final String smiley_41 = "[(g)]";
	public static final String smiley_42 = "[(h)]";
	public static final String smiley_43 = "[(i)]";
	public static final String smiley_44 = "[(j)]";
	public static final String smiley_45 = "[(k)]";
	public static final String smiley_46 = "[(l)]";
	public static final String smiley_47 = "[(m)]";
	public static final String smiley_48 = "[(n)]";
	public static final String smiley_49 = "[(o)]";
	public static final String smiley_50 = "[(p)]";
	public static final String smiley_51 = "[(q)]";
	public static final String smiley_52 = "[(r)]";
	public static final String smiley_53 = "[(s)]";
	public static final String smiley_54 = "[(t)]";
	public static final String smiley_55 = "[(u)]";
	public static final String smiley_56 = "[(v)]";
	public static final String smiley_57 = "[(w)]";
	public static final String smiley_58 = "[(x)]";
	public static final String smiley_59 = "[(y)]";
	public static final String smiley_60 = "[(z)]";
	public static final String smiley_61 = "[(aa)]";
	public static final String smiley_62 = "[(ab)]";
	public static final String smiley_63 = "[(ac)]";
	public static final String smiley_64 = "[(ad)]";
	public static final String smiley_65 = "[(ae)]";
	public static final String smiley_66 = "[(af)]";
	public static final String smiley_67 = "[(aj)]";
	public static final String smiley_68 = "[(ak)]";
	public static final String smiley_69 = "[(al)]";
	public static final String smiley_70 = "[(am)]";
	public static final String smiley_71 = "[(an)]";
	public static final String smiley_72 = "[(aq)]";
	public static final String smiley_73 = "[ao)]";
	public static final String smiley_74 = "[(ap)]";
	public static final String smiley_75 = "[(ai)]";
	public static final String smiley_76 = "[(ar)]";
	public static final String smiley_77 = "[(as)]";
	public static final String smiley_78 = "[(at)]";
	public static final String smiley_79 = "[(au)]";
	public static final String smiley_80 = "[(av)]";
	public static final String smiley_81 = "[(aw)]";
	public static final String smiley_82 = "[(ay)]";
	public static final String smiley_83 = "[(az)]";
	public static final String smiley_84 = "[(ba)]";
	public static final String smiley_85 = "[(bb)]";
	public static final String smiley_86 = "[(be)]";
	public static final String smiley_87 = "[(bf)]";
	public static final String smiley_88 = "[(bg)]";
	public static final String smiley_89 = "[(bh)]";
	public static final String smiley_90 = "[(bi)]";
	public static final String smiley_91 = "[(bj)]";
	public static final String smiley_92 = "[(bk)]";
	public static final String smiley_93 = "[(bl)]";
	public static final String smiley_94 = "[(bm)]";
	public static final String smiley_95 = "[(bn)]";
	public static final String smiley_96 = "[(bo)]";
	public static final String smiley_97 = "[(bp)]";
	public static final String smiley_98 = "[(bq)]";
	public static final String smiley_99 = "[(br)]";
	public static final String smiley_100= "[(bs)]";
	public static final String smiley_101= "[(bt)]";
	public static final String smiley_102= "[(bu)]";
	public static final String smiley_103= "[(bv)]";
	public static final String smiley_104= "[(bw)]";

	private static final Factory spannableFactory = Factory
	        .getInstance();
	
	private static final Map<Pattern, Integer> emoticons = new HashMap<Pattern, Integer>();

	static {
		
	    addPattern(emoticons, smiley_0, R.drawable.smiley_0);
	    addPattern(emoticons, smiley_1, R.drawable.smiley_1);
	    addPattern(emoticons, smiley_2, R.drawable.smiley_2);
	    addPattern(emoticons, smiley_3, R.drawable.smiley_3);
	    addPattern(emoticons, smiley_4, R.drawable.smiley_4);
	    addPattern(emoticons, smiley_5, R.drawable.smiley_5);
	    addPattern(emoticons, smiley_6, R.drawable.smiley_6);
	    addPattern(emoticons, smiley_7, R.drawable.smiley_7);
	    addPattern(emoticons, smiley_8, R.drawable.smiley_8);
	    addPattern(emoticons, smiley_9, R.drawable.smiley_9);
	    addPattern(emoticons, smiley_10, R.drawable.smiley_10);
	    addPattern(emoticons, smiley_11, R.drawable.smiley_11);
	    addPattern(emoticons, smiley_12, R.drawable.smiley_12);
	    addPattern(emoticons, smiley_13, R.drawable.smiley_13);
	    addPattern(emoticons, smiley_14, R.drawable.smiley_14);
	    addPattern(emoticons, smiley_15, R.drawable.smiley_15);
	    addPattern(emoticons, smiley_16, R.drawable.smiley_16);
	    addPattern(emoticons, smiley_17, R.drawable.smiley_17);
	    addPattern(emoticons, smiley_18, R.drawable.smiley_18);
	    addPattern(emoticons, smiley_19, R.drawable.smiley_19);
	    addPattern(emoticons, smiley_20, R.drawable.smiley_20);
	    addPattern(emoticons, smiley_21, R.drawable.smiley_21);
	    addPattern(emoticons, smiley_22, R.drawable.smiley_22);
	    addPattern(emoticons, smiley_23, R.drawable.smiley_23);
	    addPattern(emoticons, smiley_24, R.drawable.smiley_24);
	    addPattern(emoticons, smiley_25, R.drawable.smiley_25);
	    addPattern(emoticons, smiley_26, R.drawable.smiley_26);
	    addPattern(emoticons, smiley_27, R.drawable.smiley_27);
	    addPattern(emoticons, smiley_28, R.drawable.smiley_28);
	    addPattern(emoticons, smiley_29, R.drawable.smiley_29);
	    addPattern(emoticons, smiley_30, R.drawable.smiley_30);
	    addPattern(emoticons, smiley_31, R.drawable.smiley_31);
	    addPattern(emoticons, smiley_32, R.drawable.smiley_32);
	    addPattern(emoticons, smiley_33, R.drawable.smiley_33);
	    addPattern(emoticons, smiley_34, R.drawable.smiley_34);
	    addPattern(emoticons, smiley_35, R.drawable.smiley_35);
	    addPattern(emoticons, smiley_36, R.drawable.smiley_36);
	    addPattern(emoticons, smiley_37, R.drawable.smiley_37);
	    addPattern(emoticons, smiley_38, R.drawable.smiley_38);
	    addPattern(emoticons, smiley_39, R.drawable.smiley_39);
	    addPattern(emoticons, smiley_40, R.drawable.smiley_40);
	    addPattern(emoticons, smiley_41, R.drawable.smiley_41);
	    addPattern(emoticons, smiley_42, R.drawable.smiley_42);
	    addPattern(emoticons, smiley_43, R.drawable.smiley_43);
	    addPattern(emoticons, smiley_44, R.drawable.smiley_44);
	    addPattern(emoticons, smiley_45, R.drawable.smiley_45);
	    addPattern(emoticons, smiley_46, R.drawable.smiley_46);
	    addPattern(emoticons, smiley_47, R.drawable.smiley_47);
	    addPattern(emoticons, smiley_48, R.drawable.smiley_48);
	    addPattern(emoticons, smiley_49, R.drawable.smiley_49);
	    addPattern(emoticons, smiley_50, R.drawable.smiley_50);
	    addPattern(emoticons, smiley_51, R.drawable.smiley_51);
	    addPattern(emoticons, smiley_52, R.drawable.smiley_52);
	    addPattern(emoticons, smiley_53, R.drawable.smiley_53);
	    addPattern(emoticons, smiley_54, R.drawable.smiley_54);
	    addPattern(emoticons, smiley_55, R.drawable.smiley_55);
	    addPattern(emoticons, smiley_56, R.drawable.smiley_56);
	    addPattern(emoticons, smiley_57, R.drawable.smiley_57);
	    addPattern(emoticons, smiley_58, R.drawable.smiley_58);
	    addPattern(emoticons, smiley_59, R.drawable.smiley_59);
	    addPattern(emoticons, smiley_60, R.drawable.smiley_60);
	    addPattern(emoticons, smiley_61, R.drawable.smiley_61);
	    addPattern(emoticons, smiley_62, R.drawable.smiley_62);
	    addPattern(emoticons, smiley_63, R.drawable.smiley_63);
	    addPattern(emoticons, smiley_64, R.drawable.smiley_64);
	    addPattern(emoticons, smiley_65, R.drawable.smiley_65);
	    addPattern(emoticons, smiley_66, R.drawable.smiley_66);
	    addPattern(emoticons, smiley_67, R.drawable.smiley_67);
	    addPattern(emoticons, smiley_68, R.drawable.smiley_68);
	    addPattern(emoticons, smiley_69, R.drawable.smiley_69);
	    addPattern(emoticons, smiley_70, R.drawable.smiley_70);
	    addPattern(emoticons, smiley_71, R.drawable.smiley_71);
	    addPattern(emoticons, smiley_72, R.drawable.smiley_72);
	    addPattern(emoticons, smiley_73, R.drawable.smiley_73);
	    addPattern(emoticons, smiley_74, R.drawable.smiley_74);
	    addPattern(emoticons, smiley_75, R.drawable.smiley_75);
	    addPattern(emoticons, smiley_76, R.drawable.smiley_76);
	    addPattern(emoticons, smiley_77, R.drawable.smiley_77);
	    addPattern(emoticons, smiley_78, R.drawable.smiley_78);
	    addPattern(emoticons, smiley_79, R.drawable.smiley_79);
	    addPattern(emoticons, smiley_80, R.drawable.smiley_80);
	    addPattern(emoticons, smiley_81, R.drawable.smiley_81);
	    addPattern(emoticons, smiley_82, R.drawable.smiley_82);
	    addPattern(emoticons, smiley_83, R.drawable.smiley_83);
	    addPattern(emoticons, smiley_84, R.drawable.smiley_84);
	    addPattern(emoticons, smiley_85, R.drawable.smiley_85);
	    addPattern(emoticons, smiley_86, R.drawable.smiley_86);
	    addPattern(emoticons, smiley_87, R.drawable.smiley_87);
	    addPattern(emoticons, smiley_88, R.drawable.smiley_88);
	    addPattern(emoticons, smiley_89, R.drawable.smiley_89);
	    addPattern(emoticons, smiley_90, R.drawable.smiley_90);
	    addPattern(emoticons, smiley_91, R.drawable.smiley_91);
	    addPattern(emoticons, smiley_92, R.drawable.smiley_92);
	    addPattern(emoticons, smiley_93, R.drawable.smiley_93);
	    addPattern(emoticons, smiley_94, R.drawable.smiley_94);
	    addPattern(emoticons, smiley_95, R.drawable.smiley_95);
	    addPattern(emoticons, smiley_96, R.drawable.smiley_96);
	    addPattern(emoticons, smiley_97, R.drawable.smiley_97);
	    addPattern(emoticons, smiley_98, R.drawable.smiley_98);
	    addPattern(emoticons, smiley_99, R.drawable.smiley_99);
	    addPattern(emoticons, smiley_100, R.drawable.smiley_100);
	    addPattern(emoticons, smiley_101, R.drawable.smiley_101);
	    addPattern(emoticons, smiley_102, R.drawable.smiley_102);
	    addPattern(emoticons, smiley_103, R.drawable.smiley_103);
	    addPattern(emoticons, smiley_104, R.drawable.smiley_104);

	}

	private static void addPattern(Map<Pattern, Integer> map, String smile,
	        int resource) {
	    map.put(Pattern.compile(Pattern.quote(smile)), resource);
	}

	/**
	 * replace existing spannable with smiles
	 * @param context
	 * @param spannable
	 * @return
	 */
	public static boolean addSmiles(Context context, Spannable spannable) {
	    boolean hasChanges = false;
	    for (Entry<Pattern, Integer> entry : emoticons.entrySet()) {
	        Matcher matcher = entry.getKey().matcher(spannable);
	        while (matcher.find()) {
	            boolean set = true;
	            for (ImageSpan span : spannable.getSpans(matcher.start(),
	                    matcher.end(), ImageSpan.class))
	                if (spannable.getSpanStart(span) >= matcher.start()
	                        && spannable.getSpanEnd(span) <= matcher.end())
	                    spannable.removeSpan(span);
	                else {
	                    set = false;
	                    break;
	                }
	            if (set) {
	                hasChanges = true;
	                spannable.setSpan(new ImageSpan(context, entry.getValue()),
	                        matcher.start(), matcher.end(),
	                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	            }
	        }
	    }
	    return hasChanges;
	}

	public static Spannable getSmiledText(Context context, CharSequence text) {
	    Spannable spannable = spannableFactory.newSpannable(text);
	    addSmiles(context, spannable);
	    return spannable;
	}
	
	public static boolean containsKey(String key){
		boolean b = false;
		for (Entry<Pattern, Integer> entry : emoticons.entrySet()) {
	        Matcher matcher = entry.getKey().matcher(key);
	        if (matcher.find()) {
	        	b = true;
	        	break;
	        }
		}
		
		return b;
	}
	
	
	
}
