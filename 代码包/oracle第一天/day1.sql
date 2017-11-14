---scott    hr------
----tiger--------

-------------解锁用户 ---------------
alter user scott account unlock;
------------修改密码--------------
alter user scott identified by tiger;


----------------------切换用户-------------------------


----dept     部门 表

---deptno  部门编号
---dname   部门名称
---loc     部门城市

------emp     员工信息

---empno   员工编号
---ename   员工姓名
---job     职位
---mgr     领导编号
---hireate  入职时间
---sal      工资
---comm     奖金
---dpetno   部门编号

----tree     树形结构


-----------salgrade   工资等级
---grade   工资级别
---losal   级别的工资下限
---hisal   级别的工资上限



---------------查询一下员工信息---------------
select * from emp ;
select empno ,ename ,job from emp ;

-----""  只能用户修饰别名 其他地用单引号
select empno as eno ,ename  ee,job  "职位" from emp ;

--???-----

/**
 中文乱码问题解决
1.查看服务器端编码
select userenv('language') from dual;
我实际查到的结果为:AMERICAN_AMERICA.ZHS16GBK
2.执行语句 select * from V$NLS_PARAMETERS 
查看第一行中PARAMETER项中为NLS_LANGUAGE 对应的VALUE项中是否和第一步得到的值一样。
如果不是，需要设置环境变量.
否则PLSQL客户端使用的编码和服务器端编码不一致,插入中文时就会出现乱码.
3.设置环境变量
计算机->属性->高级系统设置->环境变量->新建
设置变量名:NLS_LANG,变量值:第1步查到的值， 我的是	AMERICAN_AMERICA.ZHS16GBK
4.重新启动PLSQL,插入数据正常
*/



----查询一下 年薪-------------
----sql语句支持四则运算
select sal *13 from emp ;


-----连接字符
select concat(empno,ename) from emp ;

select empno|| '====' ||ename from emp ;


-----去重复
select distinct job from emp;


--条件查询
--查询用户编号为 7369的员工

select * from emp  e where e.empno=7369;


--查询奖金 comm不为空的员工
---null 很特殊   null 不等于null  ，null 跟谁运算谁就变成null
select * from emp e where  e.comm  is not null;

select e.sal *16 + nvl(e.comm,0),e.comm  from emp e; 


--查询奖金comm为空并且工资大于 1500
select * from emp e where e.comm is null and e.sal>1500;



--查询奖金comm为空并且工资不大于 1500

select * from emp e where e.comm is null and  e.sal <=1500;
select * from emp e where e.comm is null and  not(e.sal >1500);


--查询奖金commm为空或者工资大于 1500
select * from emp e where e.comm is null  or  e.sal >1500;


--范围查询
--查询工资大于1500 并且小于3000 

select * from emp e where e.sal >1500 and e.sal<3000;
------between  包含临界点
select * from emp e where e.sal between 1500 and 3000;



--查询员工编号 是  7369  7788  7654 的员工
select * from emp e where e.empno=7369 or e.empno=7788 or e.empno=7654;

select * from emp e where e.empno in (7369,7788,7654);

--查询员工姓名是  SMTH   MARTIN   SCOTT，注意不能不加引号而且必须加单引号

select * from emp e where e.ename  in ('SMITH','MARTIN','SCOTT');



--查询员工编号不等于  7369的员工

select * from emp e where e.empno !=7369;
select * from emp e where e.empno <>7369;



--排序  

--按工资排序
---升序
select * from emp e order by e.sal;
select * from emp e order by e.sal asc;
---降序
select * from emp e order by e.sal desc;


--按奖金排序  降序

select * from emp e order by e.comm desc  nulls last;

select * from emp e order by e.comm desc  nulls first;



--模糊查询

--查询员工姓名中 带M的

select * from emp e where e.ename like  '%M%';

------oracle  数据区分大小写 而 关键字不区分
select * from emp e where e.ename like  '%m%';
SELECT * FROM EMP E WHERE E.ENAME LIKE  '%M%';

--查询员工姓名第二个字母是M的员工
select * from emp e where e.ename like  '_M%';

--查询员工姓名第三个字母是M的员工
select * from emp e where e.ename like  '__M%';


------查询员工姓名带_的员工，其中r代表转义字符，记得加引号，记得加百分号
select * from emp e where e.ename like '%r_%'  escape 'r';



--查询每个员工姓名有多少位
select length(ename),ename from emp;

----------单行函数    调用执行完  几行数据还是几行数据(也就是不合并) ---------------

----字符函数
---小写
select lower( ename) from emp ;

----大写
select upper( ename) from emp ;

------------行数不变，展示规定内容即可
select upper('abcdef') from emp ;

-----伪表  虚表  用来补全语法(dual就是虚表)(只有一行一列)
select upper('abcdef') from dual;

----字符连接
select concat( concat('abc','df'),'bvv') from dual;

-----去空格 
select trim('  agc  vvv    ') from dual;
----替换
select replace('  agc  vvv    ',' ','') from dual;


----数值函数(就是和数值有关的)
round
----保留小数  四舍五入(第二个参数表示要取的小数位数)
select  round(3.141592653589793238462643383279,4)   from dual;

trunc
----保留小数   截断
select  trunc(3.141592653589793238462643383279,4)   from dual;


mod
----取模取余------
select mod (4,3) from dual;



-----日期函数
---获取当前日期
select sysdate from dual;


-----日期可以进行四则运算
select sysdate +1 from dual; 

-----获取下周日期
select sysdate +7 from dual; 


-----获取下月日期
select  add_months( sysdate ,1) from dual; 

-----获取下年日期
select  add_months( sysdate ,12) from dual; 


-----查员工入职的天数
select round(sysdate - e.hiredate) from emp e;

-----查员工入职的周数
select round((sysdate - e.hiredate)/7) from emp e;

-----查员工入职的月数    注意和上面的区别是这两个参数用逗号间隔而不是用减号间隔
select round(months_between(sysdate,e.hiredate))  from emp e; 



-----转换函数

---字符转数字  注意是to_number而不是to_num
select '123' ,to_number('123') from dual;
---数字转字符
select 123,to_char(123) from dual;

select * from emp e where e.empno=7369;
select * from emp e where e.empno='7369';



---日期转字符  前面的是mm后面是mi是因为oracle不区分大小写。第二个参数是要转换成的字符串的规定格式。前面用-间隔，中间用空格间隔，后面用:间隔
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual;


---字符转日期  第二个参数是输入的字符串所遵守的规则，方便系统对字符串进行相应规则的解读和转换
select to_date('2017-08-25 12:06:09','yyyy-mm-dd hh24:mi:ss') from dual;



-----通用函数
select e.sal * 12  + nvl(e.comm,0) from emp e; 



----表达式----   行转列   如果不写where deptno=10的话那些不满足条件的ename就会都变成空，但是还是会显示出来  姓名是别名所以是双引号
select case ename 
when 'CLARK' then  '鲁班七号'
when 'KING' then  '黄忠'
when 'MILLER' then  '安其拉'    
end  "姓名"
from emp where deptno=10;


-----oracle 特有表达式(不推荐在工作中使用)(name很容易理解，就是别名)(都用逗号间隔，不好分辨)
select decode( ename 
, 'CLARK' ,  '鲁班七号'
, 'KING' ,  '黄忠'
, 'MILLER' ,  '安其拉'    
)  "姓名"
from emp where deptno=10;//从from开始就和上面的通用方法是一样的





----------多行函数（聚合函数） 调用执行完  几行数据变成一行(对应于单行函数) --------------

count
----查询员工数量
select count(*) from emp ;
select count(1) from emp ;
select count(999) from emp ;
select count(0) from emp ;
select count('其他') from emp ;
select count(empno) from emp ;


select count(comm) from emp ;


sum
---查询员工工资和
select sum(sal) from emp ;

avg
---查询员工工资平均数
select avg(sal) from emp ;


max
---查询员工最高工资
select max(sal) from emp ;
--min 
---查询员工最低工资
select min(sal) from emp ;



-------分组 ---------------
----按部门查询员工数量(deptno表示的是部门)
-----如果使用group by ，select 后只能出现 聚合函数 或者 group by  列
select count(1) from emp group by deptno;

select count(1),deptno from emp group by deptno;


----按部门查询员工数量 获取数量大于5的
----having 对分组后的结果做再次过滤
select count(1),deptno from emp group by deptno having count(1)>5;

----having  出现在在group 之后
----where   出现在在group 之前 






