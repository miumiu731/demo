package com.hperson.demo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * String工具类
 *
 * @author 徐琛
 */
public class StringUtils {
	public static final String EMPTY = "";

	public static boolean isNull(String str) {
		return str == null;
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	public static String nullToEmpty(String str) {
		return isNull(str) ? "" : str;
	}

	public static boolean isEmpty(String str) {
		return isNull(str) || str.isEmpty();
	}

	public static boolean isTrimEmpty(String str) {
		return isNull(str) || str.trim().isEmpty();
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isNotTrimEmpty(String str) {
		return !isTrimEmpty(str);
	}

	public static String trimToEmpty(String str) {
		return isTrimEmpty(str) ? "" : str;
	}

	public static String toObjectString(Object object) {
		return ToStringBuilder.reflectionToString(object);
	}

	public static boolean equals(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		} else {
			return s1.equals(s2);
		}
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		} else {
			return s1.equalsIgnoreCase(s2);
		}
	}

	public static String replace(String string, String oldString, String newString) {
		if (string == null) {
			return "";
		} else {
			int i = 0;
			if ((i = string.indexOf(oldString, i)) < 0) {
				return string;
			} else {
				char[] string2 = string.toCharArray();
				char[] newString2 = newString.toCharArray();
				StringBuilder buf = new StringBuilder(string2.length);
				buf.append(string2, 0, i).append(newString2);
				int oldStrLength = oldString.length();
				i += oldStrLength;

				int j;
				for (j = i; (i = string.indexOf(oldString, i)) > 0; j = i) {
					buf.append(string2, j, i - j).append(newString2);
					i += oldStrLength;
				}

				return buf.append(string2, j, string2.length - j).toString();
			}
		}
	}

	public static String replace(String string, String oldString, String newString, int[] count) {
		if (string == null) {
			return "";
		} else {
			int i = 0;
			if ((i = string.indexOf(oldString, i)) < 0) {
				return string;
			} else {
				char[] string2 = string.toCharArray();
				char[] newString2 = newString.toCharArray();
				StringBuilder buf = new StringBuilder(string2.length);
				buf.append(string2, 0, i).append(newString2);
				int counter = 1;
				int oldStrLength = oldString.length();
				i += oldStrLength;

				int j;
				for (j = i; (i = string.indexOf(oldString, i)) > 0; j = i) {
					++counter;
					buf.append(string2, j, i - j).append(newString2);
					i += oldStrLength;
				}

				count[0] = counter;
				buf.append(string2, j, string2.length - j);
				return buf.toString();
			}
		}
	}

	public static String replaceIgnoreCase(String string, String oldString, String newString) {
		if (string == null) {
			return "";
		} else {
			String lcString = string.toLowerCase();
			String lcOldString = oldString.toLowerCase();
			int i = 0;
			if ((i = lcString.indexOf(lcOldString, i)) < 0) {
				return string;
			} else {
				char[] string2 = string.toCharArray();
				char[] newString2 = newString.toCharArray();
				StringBuilder buf = new StringBuilder(string2.length);
				buf.append(string2, 0, i).append(newString2);
				int oldStrLength = oldString.length();
				i += oldStrLength;

				int j;
				for (j = i; (i = lcString.indexOf(lcOldString, i)) > 0; j = i) {
					buf.append(string2, j, i - j).append(newString2);
					i += oldStrLength;
				}

				return buf.append(string2, j, string2.length - j).toString();
			}
		}
	}

	public static String replaceIgnoreCase(String string, String oldString, String newString, int[] count) {
		if (string == null) {
			return "";
		} else {
			String lcString = string.toLowerCase();
			String lcOldString = oldString.toLowerCase();
			int i = 0;
			if ((i = lcString.indexOf(lcOldString, i)) < 0) {
				return string;
			} else {
				char[] string2 = string.toCharArray();
				char[] newString2 = newString.toCharArray();
				StringBuilder buf = new StringBuilder(string2.length);
				buf.append(string2, 0, i).append(newString2);
				int counter = 1;
				int oldStrLength = oldString.length();
				i += oldStrLength;

				int j;
				for (j = i; (i = lcString.indexOf(lcOldString, i)) > 0; j = i) {
					++counter;
					buf.append(string2, j, i - j).append(newString2);
					i += oldStrLength;
				}

				count[0] = counter;
				buf.append(string2, j, string2.length - j);
				return buf.toString();
			}
		}
	}

	public static int strLength(String s, String charsetName) {
		if (isEmpty(s)) {
			return 0;
		} else {
			try {
				return s.getBytes(charsetName).length;
			} catch (UnsupportedEncodingException var3) {
				return s.getBytes().length;
			}
		}
	}

	public static String substring(String s, int length, String charsetName) {
		try {
			byte[] bytes = nullToEmpty(s).getBytes(charsetName);
			if (bytes.length <= length) {
				return s;
			} else if (length < 1) {
				return "";
			} else {
				int len = s.length();

				for (int i = 0; i < len; ++i) {
					int iDestLength = strLength(s.substring(0, i + 1), charsetName);
					if (iDestLength > length) {
						if (i == 0) {
							return "";
						}

						return s.substring(0, i);
					}
				}

				return s;
			}
		} catch (UnsupportedEncodingException var7) {
			return "";
		}
	}

	public static String substring(String s, int length, String dot, String charsetName) {
		byte[] bytes = nullToEmpty(s).getBytes();
		if (bytes.length <= length) {
			return s;
		} else {
			int len = length - dot.length();
			if (len < 1) {
				len = 1;
			}

			return substring(s, len, charsetName) + dot;
		}
	}

	public static String md5(String s) {
		byte[] digests = null;
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(s.getBytes());
			digests = messageDigest.digest();
		} catch (Exception var6) {
			;
		}

		StringBuffer buf = new StringBuffer();

		for (int offset = 0; offset < digests.length; ++offset) {
			int digest = digests[offset];
			if (digest < 0) {
				digest += 256;
			}

			if (digest < 16) {
				buf.append("0");
			}

			buf.append(Integer.toHexString(digest));
		}

		return buf.toString();
	}

	public static boolean hasChineseCharset(String s) {
		if (s != null) {
			for (int i = 0; i < s.length(); ++i) {
				if (s.codePointAt(i) >= 256) {
					return true;
				}
			}
		}

		return false;
	}

	public static String getUUID() {
		return (UUID.randomUUID() + "").replaceAll("-", "");
	}

	public static String b2q(String str) {
		str = nullToEmpty(str);
		if (str.indexOf("\"") != -1 || str.indexOf("'") != -1) {
			int isq = 0;
			int idq = 0;

			for (int i = 0; i < str.length(); ++i) {
				if (str.charAt(i) == '\'') {
					++isq;
					if (isq % 2 == 0) {
						str = str.replaceFirst("'", "’");
					} else {
						str = str.replaceFirst("'", "‘");
					}
				} else if (str.charAt(i) == '"') {
					++idq;
					if (idq % 2 == 0) {
						str = str.replaceFirst("\"", "”");
					} else {
						str = str.replaceFirst("\"", "“");
					}
				}
			}
		}

		return str;
	}
    public static boolean isEmpty(Object str) {
        return str == null || str.toString().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj obj
     * @return boolean
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object anObject : object) {
                if (!isNullOrEmpty(anObject)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    /**
     * 生成key
     *
     * @param prefix     前缀
     * @param className  类名
     * @param methodName 方法名
     * @return prefix className.methodName
     */
    public static String genKey(String prefix, String className, String methodName) {
        return prefix + "userId_" +
                "_" +
                className +
                "." +
                methodName;
    }
    
}
