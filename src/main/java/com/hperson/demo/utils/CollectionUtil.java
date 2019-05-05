//package com.hperson.demo.utils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.ArrayUtils;
//
// 
///**
// * 集合操作工具类
// *
// * @since 1.0
// */
//public class CollectionUtil {
// 
//	private CollectionUtil() {
//		throw new IllegalStateException("Utility class");
//	}
// 
//	/**
//	 * 判断集合是否非空
//	 */
//	public static boolean isNotEmpty(Collection<?> collection) {
//		return CollectionUtils.isNotEmpty(collection);
//	}
// 
//	/**
//	 * 判断集合是否为空
//	 */
//	public static boolean isEmpty(Collection<?> collection) {
//		return CollectionUtils.isEmpty(collection);
//	}
// 
//	public static <T> List<T> deepCopy(List<T> src) {
//		if (src == null) {
//			return null;
//		}
//		return src.stream().collect(Collectors.toList());
//	}
// 
//	/**
//	 * 获取交集
//	 * 交集的获取方法:
//	 * 例:
//	 * 集合A:{1,2,3}
//	 * 集合B:{3,4}
//	 * 先获取集合A和集合B的差集C:{1,2}
//	 * 再获取集合A和差集C的差集D:{3},差集D就是交集
//	 * Added By Fangxm 2017.06.06
//	 * @param from 集合A
//	 * @param src 集合B
//	 * @return
//	 * @throws ClassNotFoundException
//	 * @throws IOException
//	 */
//	public static <T> List<T> intersection(final List<T> a, final List<T> b) {
//		if (a == null || a.isEmpty()) {
//			return null;
//		}
//		if (b == null || b.isEmpty()) {
//			return null;
//		}
//		ArrayList<T> list = new ArrayList<>();
//		Map<T, Integer> mapa = getCardinalityMap(a);
//		Map<T, Integer> mapb = getCardinalityMap(b);
//		Set<T> elts = new HashSet<>(a);
//		elts.addAll(b); // addAll时会自动去重
//		Iterator<T> it = elts.iterator();
//		while (it.hasNext()) {
//			T obj = it.next();
//			// 由于去过重了, list要add的次数是两者的较小值
//			for (int i = 0, m = Math.min(getFreq(obj, mapa), getFreq(obj, mapb)); i < m; i++) {
//				list.add(obj);
//			}
//		}
//		return list;
//	}
//	/**
//	 * 返回拥有相同obj的数量
//	 * @param coll
//	 * @return
//	 */
//	public static <T> Map<T, Integer> getCardinalityMap(final List<T> coll) {
//		Map<T, Integer> count = new HashMap<>();
//		for (Iterator<T> it = coll.iterator(); it.hasNext();) {
//			T obj = it.next();
//			Integer c = count.get(obj);
//			if (c == null) {
//				count.put(obj, 1);
//			} else {
//				count.put(obj, c.intValue() + 1);
//			}
//		}
//		return count;
//	}
//	private static final <T> int getFreq(final T obj, final Map<T, Integer> freqMap) {
//		Integer count = freqMap.get(obj);
//		if (count != null) {
//			return count.intValue();
//		}
//		return 0;
//	}
// 
//	/**
//	 * 获取差集
//	 * 例:
//	 * 集合A:{1,2,3}
//	 * 集合B:{3,4}
//	 * 集合A和集合B的差集C:{1,2}
//	 * Added By Fangxm 2017.06.06
//	 */
//	public static <T> List<T> differenceSet(List<T> from, List<T> src) {
//		if (src == null || src.isEmpty()) {
//			return from;
//		}
//		if (from == null || from.isEmpty()) {
//			return from;
//		}
//		List<T> dest = deepCopy(from);
//		return removeAll(dest, src);
//	}
// 
//	/**
//	 * 一个比ArrayList效率更高的方法, ArrayList的removeAll会循环套循环, 处理效率会随着减数的变大而降低
//	 * 改成用Map来处理, 可以有效降低处理次数
//	 * 返回差集
//	 * @param src 被减数
//	 * @param tar 减数
//	 * @return
//	 */
//	public static <T> List<T> removeAll(List<T> src, List<T> tar) {
//		if (src == null || src.isEmpty()) {
//			return src;
//		}
//		if (tar == null || tar.isEmpty()) {
//			return src;
//		}
//		ArrayList<T> list = new ArrayList<>();
//		Map<T, Integer> mapa = getCardinalityMap(src);
//		Map<T, Integer> mapb = getCardinalityMap(tar);
//		Set<T> elts = new HashSet<>(src);
//		Iterator<T> it = elts.iterator();
//		while (it.hasNext()) {
//			T obj = it.next();
//			int srcCount = getFreq(obj, mapa);
//			int tarCount = getFreq(obj, mapb);
//			if (srcCount > 0 && tarCount <= 0) {
//				for (int i = 0; i < srcCount; i++) {
//					list.add(obj);
//				}
//			}
//		}
//		src.clear();
//		src.addAll(list);
//		return list;
//	}
// 
//	private static transient final String DEFAULT_SPLIT_STR = ",";
//
//    /**
//     * 数组转列表
//     *
//     * @param arr
//     *            an array of T objects.
//     * @param <T>
//     *            a T object.
//     * @return a {@link java.util.List} object.
//     */
//    public static final <T> List<T> array2List(T[] arr) {
//        if (ArrayUtils.isEmpty(arr)) {
//            return null;
//        }
//        return Arrays.asList(arr);
//    }
//
//    /**
//     * 数组转SET
//     *
//     * @param arr
//     *            an array of T objects.
//     * @param <T>
//     *            a T object.
//     * @return a {@link java.util.Set} object.
//     */
//    public static final <T> Set<T> array2Set(T[] arr) {
//        if (ArrayUtils.isEmpty(arr)) {
//            return null;
//        }
//        return new LinkedHashSet<T>(Arrays.asList(arr));
//    }
//
//    /**
//     * 合并集合
//     *
//     * @param collections
//     *            a {@link java.util.Collection} object.
//     * @param <T>
//     *            a T object.
//     * @return a {@link java.util.List} object.
//     */
//    @SuppressWarnings("unchecked")
//    public static final <T> List<T> collections2List(
//            Collection<T>... collections) {
//        if (ArrayUtils.isEmpty(collections)) {
//            return null;
//        }
//
//        final List<T> li = new ArrayList<T>();
//        for (Collection<T> foo : collections) {
//            if (CollectionUtils.isNotEmpty(foo)) {
//                li.addAll(foo);
//            }
//        }
//        return li;
//    }
//
//    /**
//     * 合并集合
//     *
//     * @param collections
//     *            a {@link java.util.Collection} object.
//     * @param <T>
//     *            a T object.
//     * @return a {@link java.util.Set} object.
//     */
//    @SuppressWarnings("unchecked")
//    public static final <T> Set<T> collections2Set(Collection<T>... collections) {
//        if (ArrayUtils.isEmpty(collections)) {
//            return null;
//        }
//        final Set<T> set = new LinkedHashSet<T>();
//        for (Collection<T> foo : collections) {
//            if (CollectionUtils.isNotEmpty(foo)) {
//                set.addAll(foo);
//            }
//        }
//        return set;
//    }
//
//    /**
//     * 拼接集合字符串
//     *
//     * @param collection
//     *            a {@link java.util.Collection} object.
//     * @param joinStr
//     *            a {@link java.lang.String} object.
//     * @return a {@link java.lang.String} object.
//     */
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static final String join(final Collection collection, final String joinStr) {
//        if (CollectionUtils.isEmpty(collection)) {
//            return "";
//        }
//        if (joinStr == null) {
//            throw new IllegalArgumentException("join string is null.");
//        }
//
//        Object[] arr = ArrayUtil.toArray(collection, Object.class);
//        return ArrayUtil.join(arr, joinStr);
//    }
//
//    /**
//     * 拼接集合字符串
//     *
//     * @param collection
//     *            a {@link java.util.Collection} object.
//     * @return a {@link java.lang.String} object.
//     */
//    @SuppressWarnings("rawtypes")
//    public static final String join(final Collection collection) {
//        return join(collection, DEFAULT_SPLIT_STR);
//    }
//
//    /**
//     * 计算笛卡儿积
//     *
//     * @param crossArgs
//     *            a {@link java.util.List} object.
//     * @param <T>
//     *            a T object.
//     * @return a {@link java.util.List} object.
//     */
//    public static <T> List<List<T>> decartes(List<List<T>> crossArgs) {
//
//        // 计算出笛卡尔积行数
//        int rows = crossArgs.size() > 0 ? 1 : 0;
//
//        for (List<T> data : crossArgs) {
//            rows *= data.size();
//        }
//
//        // 笛卡尔积索引记录
//        int[] record = new int[crossArgs.size()];
//
//        List<List<T>> results = new ArrayList<List<T>>();
//
//        // 产生笛卡尔积
//        for (int i = 0; i < rows; i++) {
//            List<T> row = new ArrayList<T>();
//
//            // 生成笛卡尔积的每组数据
//            for (int index = 0; index < record.length; index++) {
//                row.add(crossArgs.get(index).get(record[index]));
//            }
//
//            results.add(row);
//            crossRecord(crossArgs, record, crossArgs.size() - 1);
//        }
//
//        return results;
//    }
//
//    /**
//     * @param sourceArgs
//     * @param record
//     * @param level
//     */
//    private static <T> void crossRecord(List<List<T>> sourceArgs, int[] record, int level) {
//        record[level] = record[level] + 1;
//
//        if (record[level] >= sourceArgs.get(level).size() && level > 0) {
//            record[level] = 0;
//            crossRecord(sourceArgs, record, level - 1);
//        }
//    }
//
//    /**
//     * 从list中取得某段数据
//     * 
//     * @param <T>
//     * @param datas
//     * @param page
//     * @param PAGESIZE
//     * @return
//     */
//    public static <T> List<T> getLimit(List<T> datas, int begin, int end) {
//        List<T> objects = new ArrayList<T>();
//        if (datas.size() <= begin) {
//            return objects;
//        }
//        int temp = (datas.size() > end) ? end : datas.size();
//        for (int i = begin; i < temp; i++) {
//            objects.add(datas.get(i));
//        }
//        return objects;
//    }
//}
