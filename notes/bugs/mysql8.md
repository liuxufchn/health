# MySQL8数据库`select * from t_ordersetting where orderDate between ‘2020-4-1‘ and ‘2020-4-31‘`语句不能用  
> 好像是mysql8，使用between…and…查询时，当查询的右边界为2021-4-31时，超出了实际范围（因为4月不可能有31天）
```java
// 将字符串用SimpleDateFormat转换成日期, 
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
Date date1 = sdf .parse(begin);
Date date2 = SimpleDateFormat.parse(end);
// 然后封装到map中, 就能查询出来了
Map<String,Date> map = new HashMap<>();
map.put("begin",date1);
map.put("end",date2);
```
```sql
# 或者
SELECT * 
FROM t_ordersetting 
WHERE orderDate 
BETWEEN STR_TO_DATE('2021-04-1','%Y-%m-%d') AND STR_TO_DATE('2021-04-31','%Y-%m-%d')
```

# 解决MySql8.0版本出现修改日期时间自动减一天
解决方案，修改数据库连接中的timezone为：`Asia/Shanghai`  
很tmd坑