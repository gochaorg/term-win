package xyz.cofe.term.win;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

// https://docs.oracle.com/javase/8/docs/technotes/guides/intl/encoding.doc.html
// https://docs.microsoft.com/en-us/windows/win32/intl/code-page-identifiers
public enum CodePage {
    IBM037(037, "IBM037", "IBM EBCDIC US-Canada", Charset.forName("IBM037")),
    IBM437(437, "IBM437", "OEM United States", Charset.forName("IBM437")),
    IBM500(500, "IBM500", "IBM EBCDIC International",Charset.forName("IBM500")),
    ASMO708(708, "ASMO-708", "Arabic (ASMO 708)", Charset.forName("ISO-8859-6")),
    Arabic(709, "Arabic", "Arabic (ASMO-449+, BCON V4)"),
    ArabicT(710, "Arabic", "Arabic - Transparent Arabic"),
    DOS720(720, "DOS-720", "Arabic (Transparent ASMO); Arabic (DOS)"),
    IBM737(737, "ibm737", "OEM Greek (formerly 437G); Greek (DOS)", Charset.forName("x-IBM737")),
    IBM775(775, "ibm775", "OEM Baltic; Baltic (DOS)", Charset.forName("IBM775")),  // todo find
    IBM850(850, "ibm850", "OEM Multilingual Latin 1; Western European (DOS)"),  // todo find
    IBM852(852, "ibm852", "OEM Latin 2; Central European (DOS)"),  // todo find
    IBM855(855, "IBM855", "OEM Cyrillic (primarily Russian)"),  // todo find
    IBM857(857, "ibm857", "OEM Turkish; Turkish (DOS)"),  // todo find
    IBM858(858, "IBM00858", "OEM Multilingual Latin 1 + Euro symbol"),  // todo find
    IBM860(860, "IBM860", "OEM Portuguese; Portuguese (DOS)"),  // todo find
    IBM861(861, "ibm861", "OEM Icelandic; Icelandic (DOS)"),  // todo find
    DOS862(862, "DOS-862", "OEM Hebrew; Hebrew (DOS)"),  // todo find
    IBM863(863, "IBM863", "OEM French Canadian; French Canadian (DOS)"),  // todo find
    IBM864(864, "IBM864", "OEM Arabic; Arabic (864)"),  // todo find
    IBM865(865, "IBM865", "OEM Nordic; Nordic (DOS)"), // todo find
    CP866(866, "cp866", "OEM Russian; Cyrillic (DOS)", Charset.forName("IBM866")),
    IBM869(869, "ibm869", "OEM Modern Greek; Greek, Modern (DOS)"), // todo find
    IBM870(870, "IBM870", "IBM EBCDIC Multilingual/ROECE (Latin 2); IBM EBCDIC Multilingual Latin 2"), // todo find
    WIN874(874, "windows-874", "Thai (Windows)"), // todo find
    CP875(875, "cp875", "IBM EBCDIC Greek Modern"), // todo find
    SHIFT_JIS(932, "shift_jis", "ANSI/OEM Japanese; Japanese (Shift-JIS)"), // todo find
    GB2312(936, "gb2312", "ANSI/OEM Simplified Chinese (PRC, Singapore); Chinese Simplified (GB2312)"), // todo find
    KS_C_5601_1987(949, "ks_c_5601-1987", "ANSI/OEM Korean (Unified Hangul Code)"), // todo find
    BIG5(950, "big5", "ANSI/OEM Traditional Chinese (Taiwan; Hong Kong SAR, PRC); Chinese Traditional (Big5)"), // todo find
    IBM1026(1026, "IBM1026", "IBM EBCDIC Turkish (Latin 5)"), // todo find
    IBM01047(1047, "IBM01047", "IBM EBCDIC Latin 1/Open System"), // todo find
    IBM01140(1140, "IBM01140", "IBM EBCDIC US-Canada (037 + Euro symbol); IBM EBCDIC (US-Canada-Euro)"), // todo find
    IBM01141(1141, "IBM01141", "IBM EBCDIC Germany (20273 + Euro symbol); IBM EBCDIC (Germany-Euro)"), // todo find
    IBM01142(1142, "IBM01142", "IBM EBCDIC Denmark-Norway (20277 + Euro symbol); IBM EBCDIC (Denmark-Norway-Euro)"), // todo find
    IBM01143(1143, "IBM01143", "IBM EBCDIC Finland-Sweden (20278 + Euro symbol); IBM EBCDIC (Finland-Sweden-Euro)"), // todo find
    IBM01144(1144, "IBM01144", "IBM EBCDIC Italy (20280 + Euro symbol); IBM EBCDIC (Italy-Euro)"), // todo find
    IBM01145(1145, "IBM01145", "IBM EBCDIC Latin America-Spain (20284 + Euro symbol); IBM EBCDIC (Spain-Euro)"), // todo find
    IBM01146(1146, "IBM01146", "IBM EBCDIC United Kingdom (20285 + Euro symbol); IBM EBCDIC (UK-Euro)"), // todo find
    IBM01147(1147, "IBM01147", "IBM EBCDIC France (20297 + Euro symbol); IBM EBCDIC (France-Euro)"), // todo find
    IBM01148(1148, "IBM01148", "IBM EBCDIC International (500 + Euro symbol); IBM EBCDIC (International-Euro)"), // todo find
    IBM01149(1149, "IBM01149", "IBM EBCDIC Icelandic (20871 + Euro symbol); IBM EBCDIC (Icelandic-Euro)"), // todo find
    UTF16(1200, "utf-16", "Unicode UTF-16, little endian byte order (BMP of ISO 10646); available only to managed applications"), // todo find
    UnicodeFFFE(1201, "unicodeFFFE", "Unicode UTF-16, big endian byte order; available only to managed applications"), // todo find
    WIN1250(1250, "windows-1250", "ANSI Central European; Central European (Windows)"), // todo find
    WIN1251(1251, "windows-1251", "ANSI Cyrillic; Cyrillic (Windows)", Charset.forName("windows-1251")),
    WIN1252(1252, "windows-1252", "ANSI Latin 1; Western European (Windows)"), // todo find
    WIN1253(1253, "windows-1253", "ANSI Greek; Greek (Windows)"), // todo find
    WIN1254(1254, "windows-1254", "ANSI Turkish; Turkish (Windows)"), // todo find
    WIN1255(1255, "windows-1255", "ANSI Hebrew; Hebrew (Windows)"), // todo find
    WIN1256(1256, "windows-1256", "ANSI Arabic; Arabic (Windows)"), // todo find
    WIN1257(1257, "windows-1257", "ANSI Baltic; Baltic (Windows)"), // todo find
    WIN1258(1258, "windows-1258", "ANSI/OEM Vietnamese; Vietnamese (Windows)"), // todo find
    Johab(1361, "Johab", "Korean (Johab)"), // todo find
    Macintosh(10000, "macintosh", "MAC Roman; Western European (Mac)"), // todo find
    XMacJapanese(10001, "x-mac-japanese", "Japanese (Mac)"), // todo find
    XMacChinesetrad(10002, "x-mac-chinesetrad", "MAC Traditional Chinese (Big5); Chinese Traditional (Mac)"), // todo find
    XMacKorean(10003, "x-mac-korean", "Korean (Mac)"), // todo find
    XMacArabic(10004, "x-mac-arabic", "Arabic (Mac)"), // todo find
    XMacHebrew(10005, "x-mac-hebrew", "Hebrew (Mac)"), // todo find
    XMacGreek(10006, "x-mac-greek", "Greek (Mac)"), // todo find
    XMacCyrillic(10007, "x-mac-cyrillic", "Cyrillic (Mac)", Charset.forName("x-MacCyrillic")),
    XMacChinesesimp(10008, "x-mac-chinesesimp", "MAC Simplified Chinese (GB 2312); Chinese Simplified (Mac)"), // todo find
    XMacRomanian(10010, "x-mac-romanian", "Romanian (Mac)"), // todo find
    XMacUkrainian(10017, "x-mac-ukrainian", "Ukrainian (Mac)"), // todo find
    XMacThai(10021, "x-mac-thai", "Thai (Mac)"), // todo find
    XMacCE(10029, "x-mac-ce", "MAC Latin 2; Central European (Mac)"), // todo find
    XMacIcelandic(10079, "x-mac-icelandic", "Icelandic (Mac)"), // todo find
    XMacTurkish(10081, "x-mac-turkish", "Turkish (Mac)"), // todo find
    XMacCroatian(10082, "x-mac-croatian", "Croatian (Mac)"), // todo find
    UTF32(12000, "utf-32", "Unicode UTF-32, little endian byte order; available only to managed applications", Charset.forName("UTF-32")),
    UTF32BE(12001, "utf-32BE", "Unicode UTF-32, big endian byte order; available only to managed applications", Charset.forName("UTF-32BE")),
    XChinese_CNS(20000, "x-Chinese_CNS", "CNS Taiwan; Chinese Traditional (CNS)"), // todo find
    XCP20001(20001, "x-cp20001", "TCA Taiwan"), // todo find
    XChineseEten(20002, "x_Chinese-Eten", "Eten Taiwan; Chinese Traditional (Eten)"), // todo find
    XCP20003(20003, "x-cp20003", "IBM5550 Taiwan"), // todo find
    XCP20004(20004, "x-cp20004", "TeleText Taiwan"), // todo find
    XCP20005(20005, "x-cp20005", "Wang Taiwan"), // todo find
    XIA5(20105, "x-IA5", "IA5 (IRV International Alphabet No. 5, 7-bit); Western European (IA5)"), // todo find
    xIA5German(20106, "x-IA5-German", "IA5 German (7-bit)"), // todo find
    XIA5Swedish(20107, "x-IA5-Swedish", "IA5 Swedish (7-bit)"), // todo find
    XIA5Norwegian(20108, "x-IA5-Norwegian", "IA5 Norwegian (7-bit)"), // todo find
    USAscii(20127, "us-ascii", "US-ASCII (7-bit)"), // todo find
    XCP20261(20261, "x-cp20261", "T.61"), // todo find
    XCP20269(20269, "x-cp20269", "ISO 6937 Non-Spacing Accent"), // todo find
    IBM273(20273, "IBM273", "IBM EBCDIC Germany"), // todo find
    IBM277(20277, "IBM277", "IBM EBCDIC Denmark-Norway"), // todo find
    IBM278(20278, "IBM278", "IBM EBCDIC Finland-Sweden"), // todo find
    IBM280(20280, "IBM280", "IBM EBCDIC Italy"), // todo find
    IBM284(20284, "IBM284", "IBM EBCDIC Latin America-Spain"), // todo find
    IBM285(20285, "IBM285", "IBM EBCDIC United Kingdom"), // todo find
    IBM290(20290, "IBM290", "IBM EBCDIC Japanese Katakana Extended"), // todo find
    IBM297(20297, "IBM297", "IBM EBCDIC France"), // todo find
    IBM420(20420, "IBM420", "IBM EBCDIC Arabic"), // todo find
    IBM423(20423, "IBM423", "IBM EBCDIC Greek"), // todo find
    IBM424(20424, "IBM424", "IBM EBCDIC Hebrew"), // todo find
    XEBCDICKoreanExtended(20833, "x-EBCDIC-KoreanExtended", "IBM EBCDIC Korean Extended"), // todo find
    IBMThai(20838, "IBM-Thai", "IBM EBCDIC Thai"), // todo find
    KOI8R(20866, "koi8-r", "Russian (KOI8-R); Cyrillic (KOI8-R)"), // todo find
    IBM871(20871, "IBM871", "IBM EBCDIC Icelandic"), // todo find
    IBM880(20880, "IBM880", "IBM EBCDIC Cyrillic Russian"), // todo find
    IBM905(20905, "IBM905", "IBM EBCDIC Turkish"), // todo find
    IBM00924(20924, "IBM00924", "IBM EBCDIC Latin 1/Open System (1047 + Euro symbol)"), // todo find
    EUC_JP(20932, "EUC-JP", "Japanese (JIS 0208-1990 and 0212-1990)"), // todo find
    XCP20936(20936, "x-cp20936", "Simplified Chinese (GB2312); Chinese Simplified (GB2312-80)"), // todo find
    XCP20949(20949, "x-cp20949", "Korean Wansung"), // todo find
    CP1025(21025, "cp1025", "IBM EBCDIC Cyrillic Serbian-Bulgarian"), // todo find
    _21027(21027, "", "deprecated"), // todo find
    KOI8U(21866, "koi8-u", "Ukrainian (KOI8-U); Cyrillic (KOI8-U)"), // todo find
    ISO8859_1(28591, "iso-8859-1", "ISO 8859-1 Latin 1; Western European (ISO)"), // todo find
    ISO8859_2(28592, "iso-8859-2", "ISO 8859-2 Central European; Central European (ISO)"), // todo find
    ISO8859_3(28593, "iso-8859-3", "ISO 8859-3 Latin 3"), // todo find
    ISO8859_4(28594, "iso-8859-4", "ISO 8859-4 Baltic"), // todo find
    ISO8859_5(28595, "iso-8859-5", "ISO 8859-5 Cyrillic"), // todo find
    ISO8859_6(28596, "iso-8859-6", "ISO 8859-6 Arabic"), // todo find
    ISO8859_7(28597, "iso-8859-7", "ISO 8859-7 Greek"), // todo find
    ISO8859_8(28598, "iso-8859-8", "ISO 8859-8 Hebrew; Hebrew (ISO-Visual)"), // todo find
    ISO8859_9(28599, "iso-8859-9", "ISO 8859-9 Turkish"), // todo find
    ISO8859_13(28603, "iso-8859-13", "ISO 8859-13 Estonian"), // todo find
    ISO8859_15(28605, "iso-8859-15", "ISO 8859-15 Latin 9"), // todo find
    XEuropa(29001, "x-Europa", "Europa 3"), // todo find
    ISO8859_8I(38598, "iso-8859-8-i", "ISO 8859-8 Hebrew; Hebrew (ISO-Logical)"), // todo find
    ISO2022_JP(50220, "iso-2022-jp", "ISO 2022 Japanese with no halfwidth Katakana; Japanese (JIS)"), // todo find
    CSISO2022JP(50221, "csISO2022JP", "ISO 2022 Japanese with halfwidth Katakana; Japanese (JIS-Allow 1 byte Kana)"), // todo find
    ISO2022JP(50222, "iso-2022-jp", "ISO 2022 Japanese JIS X 0201-1989; Japanese (JIS-Allow 1 byte Kana - SO/SI)"), // todo find
    ISO2022KR(50225, "iso-2022-kr", "ISO 2022 Korean"), // todo find
    XCP50227(50227, "x-cp50227", "ISO 2022 Simplified Chinese; Chinese Simplified (ISO 2022)"), // todo find
    _50229(50229, "", "ISO 2022 Traditional Chinese"), // todo find
    _50930(50930, "", "EBCDIC Japanese (Katakana) Extended"), // todo find
    _50931(50931, "", "EBCDIC US-Canada and Japanese"), // todo find
    _50933(50933, "", "EBCDIC Korean Extended and Korean"), // todo find
    _50935(50935, "", "EBCDIC Simplified Chinese Extended and Simplified Chinese"), // todo find
    _50936(50936, "", "EBCDIC Simplified Chinese"), // todo find
    _50937(50937, "", "EBCDIC US-Canada and Traditional Chinese"), // todo find
    _50939(50939, "", "EBCDIC Japanese (Latin) Extended and Japanese"), // todo find
    EUC_JP_51932(51932, "euc-jp", "EUC Japanese"), // todo find
    EUC_CN(51936, "EUC-CN", "EUC Simplified Chinese; Chinese Simplified (EUC)"), // todo find
    EUC_KR(51949, "euc-kr", "EUC Korean"), // todo find
    EUC_51950(51950, "", "EUC Traditional Chinese"), // todo find
    HZ_GB_2312(52936, "hz-gb-2312", "HZ-GB2312 Simplified Chinese; Chinese Simplified (HZ)"), // todo find
    GB18030(54936, "GB18030", "Windows XP and later: GB18030 Simplified Chinese (4 byte); Chinese Simplified (GB18030)"), // todo find
    X_ISCIIde(57002, "x-iscii-de", "ISCII Devanagari"), // todo find
    X_ISCIIbe(57003, "x-iscii-be", "ISCII Bangla"), // todo find
    X_ISCIIta(57004, "x-iscii-ta", "ISCII Tamil"), // todo find
    X_ISCIIte(57005, "x-iscii-te", "ISCII Telugu"), // todo find
    X_ISCIIas(57006, "x-iscii-as", "ISCII Assamese"), // todo find
    X_ISCIIor(57007, "x-iscii-or", "ISCII Odia"), // todo find
    X_ISCIIka(57008, "x-iscii-ka", "ISCII Kannada"), // todo find
    X_ISCIIma(57009, "x-iscii-ma", "ISCII Malayalam"), // todo find
    X_ISCIIgu(57010, "x-iscii-gu", "ISCII Gujarati"), // todo find
    X_ISCIIpa(57011, "x-iscii-pa", "ISCII Punjabi"), // todo find
    UTF7(65000, "utf-7", "Unicode (UTF-7)"), // todo find
    UTF8(65001, "utf-8", "Unicode (UTF-8)", StandardCharsets.UTF_8);

    public final int code;
    public final Optional<String> dotNetName;
    public final Optional<String> comment;
    public final Optional<Charset> charset;

    CodePage(int code, String dotNetName, String comment) {
        this.code = code;
        this.dotNetName = dotNetName==null
            ? Optional.empty()
            : dotNetName.length()==0
            ? Optional.empty()
            : Optional.of(dotNetName);
        this.comment = comment == null
            ? Optional.empty()
            : comment.length()==0
            ? Optional.empty()
            : Optional.of(comment);
        this.charset = Optional.empty();
    }

    CodePage(int code, String dotNetName, String comment, Charset javaCharset) {
        this.code = code;
        this.dotNetName = dotNetName==null
            ? Optional.empty()
            : dotNetName.length()==0
            ? Optional.empty()
            : Optional.of(dotNetName);
        this.comment = comment == null
            ? Optional.empty()
            : comment.length()==0
            ? Optional.empty()
            : Optional.of(comment);
        this.charset = javaCharset==null
            ? Optional.empty()
            : Optional.of(javaCharset);
    }

    CodePage(int code, String comment) {
        this.code = code;
        this.dotNetName = Optional.empty();
        this.comment = comment == null
            ? Optional.empty()
            : comment.length()==0
            ? Optional.empty()
            : Optional.of(comment);
        this.charset = Optional.empty();
    }

    CodePage(int code, String comment, Charset javaCharset) {
        this.code = code;
        this.dotNetName = Optional.empty();
        this.comment = comment == null
            ? Optional.empty()
            : comment.length()==0
            ? Optional.empty()
            : Optional.of(comment);
        this.charset = javaCharset==null
            ? Optional.empty()
            : Optional.of(javaCharset);
    }
}
