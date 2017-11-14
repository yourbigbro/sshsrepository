------多表查询-------------
------查询 员工信息以及员工的部门信息--------------------

---------14  *  dept  4    =56 ;
select * from emp e, dept d;

-------------笛卡尔积   没有实际意义 ，如果出现 ，说明sql 写的有问题

----等值连接
----内连接 （隐式）
select * from emp e, dept d
where  e.deptno=d.deptno ;

----------注意加上inner join之后后面的where变成了on
select * from  emp e inner join dept d  on e.deptno=d.deptno ;


----------重复的列名的话要加上别名，不重名也可以加上列名
select e.empno,d.deptno from emp e, dept d
where  e.deptno=d.deptno;

----不等值连接 (了解)
select * from emp e, dept d
where  e.deptno!=d.deptno;


--范例：查询出每个员工的上级领导 (员工编号，员工姓名，领导编号，领导姓名)(两个相同的表进行inner join 内连接)

select e1.empno "员工编号",e1.ename "员工姓名",e2.empno "领导编号",e2.ename "领导姓名" 
from emp e1,emp e2 
where e1.mgr=e2.empno;


--范例: 在上一个例子的基础上查询该员工的部门名称(注意该sql语句的条件之间用and连接)

select e1.empno "员工编号",e1.ename "员工姓名", e1.deptno "员工部门编号", d.dname "员工的部门名称",
e2.empno "领导编号",e2.ename "领导姓名" 
from emp e1,emp e2  ,dept d
where e1.mgr=e2.empno
and e1.deptno=d.deptno
;

--范例：查询出每个员工编号，姓名，部门名称，工资等级和他的上级领导的姓名，工资等级
--(注意and后面可以加between and并且不用加括号)
--(每个部分独占一行看起来更加清楚)(注意，工资和工资等级都要查询)
--(注意，e1是员工，e2是领导，所以salgrade表格也要分成s和s2分别对应员工和领导)
--(下面虽然只有两个不同类别的表，但是他是四个表联合查询)
--(由最低工资losal和最高工资hisal得到对应的工资等级grade)

select e1.empno "员工编号",e1.ename "员工姓名", e1.deptno "员工部门编号",
 d.dname "员工的部门名称", e1.sal "员工工资", s.grade "员工工资等级",
e2.empno "领导编号",e2.ename "领导姓名" ,e2.sal "领导工资" ,s2.grade "领导工资等级"
from emp e1,emp e2  ,dept d ,salgrade s,salgrade s2
where e1.mgr=e2.empno
and e1.deptno=d.deptno
and  e1.sal between s.losal and s.hisal
and e2.sal between s2.losal and s2.hisal;



--范例：查询出所有员工的上级领导(以员工为准)
--范例：查询出所有的部门下的员工，要求把没有员工的部门也展示出来(以部门为准)
------外连接

------左外  右外 -----
-----全量表
--下面这两个左右外连接效果是一样的
--(左外连接是以左边的表为主，因此以员工编号和员工姓名为主。也就是以为主的表中的列为主，也就是没有领导的员工也会列出来)
--(需要连接查询的原因是同一张表中只有领导编号没有领导姓名，因此要联合查询)
--(下面的两个左右外连接查询语句只是emp e1 left join  emp e2和emp e2 right join emp e1的区别，其他部分没有任何不同)
--(注意两张表中都是取的员工，第一张表里的领导用于连接另一张表，另一张表里的领导干脆完全没有用)
select e1.empno "员工编号",e1.ename "员工姓名",e2.empno "领导编号",e2.ename "领导姓名" 
from  emp e1 left join  emp e2 on e1.mgr=e2.empno;

select e1.empno "员工编号",e1.ename "员工姓名",e2.empno "领导编号",e2.ename "领导姓名" 
from emp e2 right join emp e1  on e1.mgr = e2.empno;


-------oracle 特有外连接------------
--(加号在左边就是右外连接，加号在右边就是左外连接)
--(和上面通用的外连接方法的区别是不再写出left join和rightjoin字样，on关键字自然也就恢复成了where关键字)
select e1.empno "员工编号",e1.ename "员工姓名",e2.empno "领导编号",e2.ename "领导姓名" 
from emp e1,emp e2 
where e1.mgr=e2.empno(+);  



-----全量表错误(以领导为主，即使是没有员工的领导也被列出来的，没有领导的领导应该是最大的领导)
select e1.empno "员工编号",e1.ename "员工姓名",e2.empno "领导编号",e2.ename "领导姓名" 
from  emp e2 left join  emp e1 on e1.mgr=e2.empno;







-------------------
--查询比SCOTT工资高的员工
----子查询    支持 一行一列   一行多列   多行多列
select sal from emp  where ename='SCOTT';
--给一个表起了别名
select e.* from emp  e where e.sal > (select sal from emp  where ename='SCOTT');
--给两个表起了别名
select e.* from emp  e where e.sal > (select p.sal from emp p where p.ename='SCOTT');
--虽然是联合查询但是只有e1中的列
select e1.* from emp e1 ,emp e2 where e2.ename='SCOTT' and e1.sal >e2.sal;


--查询职位是经理并且工资比7782号员工高的员工
select sal from emp where empno=7782;
select * from emp e where e.job='MANAGER' and e.sal > (select sal from emp where empno=7782);

--查询工资最低的员工(因此要先查询出最低工资)

select min(sal) from emp;
select * from emp e where e.sal = (select min(sal) from emp);


--查询部门最低工资大于30号部门最低工资的结果
--给每个组查询一个最低工资，所以有几个部门就有几行(也就是有几个部门最低工资)
select min(sal) from  emp group by deptno;
--30号部门的最低工资
select min(sal) from emp where deptno=30;
--group by 后面只能用having不能用where
select min(sal) from  emp group by deptno  having min(sal) >(select min(sal) from emp where deptno=30);


--查询出和scott同部门并且同职位的员工

select deptno from emp  where ename='SCOTT';

select     job from emp  where ename='SCOTT';

select * from emp e where  e.deptno=(select deptno from emp  where ename='SCOTT') 
and e.job=(select   job from emp  where ename='SCOTT');
------(这个很是特殊)
select * from emp e where ( e.deptno ,e.job ) = (select deptno,job from emp  where ename='SCOTT');


--查询每个部门的最低工资和最低工资的雇员和部门名称(注意deptno是部门编号而不是部门名称)(可以看出查询出来的结果的数量(行数)是部门的数量)
--(最低工资以及该部门的名称应该首先被查出来并且作为一个子表)
select  min(sal),deptno from emp  group by deptno;

-----(注意在括号外面访问列需要加上该列所在的表的别名作前缀)
----(dept表是为了查询部门名称，empt表是为了查询该雇员的所有信息)
----(empt表中包含员工的工资，因此自然也就包含了该部门员工的最低工资信息)
----(几个表内连接时产生的结果表的行数不会超过这几个表中行数最小的表的行数)
select e.* ,d.dname
from (select  min(sal) x ,deptno y from emp  group by deptno) t ,emp e ,dept d
where e.sal=t.x and e.deptno=t.y
and e.deptno=d.deptno
;

--查询出不是领导的员工(查询该员工的所有信息，也就是emp表的所有列)？？？？
---查询领导的编号

------在做子查询时需要先去 空值
----(查询的是所有领导的编号)
----(empno是员工编号)
----where mgr is not null排除了mgr属性为null的情况
select distinct mgr from emp;

select * from emp e where e.empno not in (select distinct mgr from emp where mgr is not null);




----------------------------------
select * from emp e where  e.empno=7369  or e.empno=7788  or  e.empno= 7782  or e.empno= null;
                        ----    true   or   true  or true   or false  =true;
select * from emp e where e.empno in (7369,7788,7782 ,null);
-------(注意集合里面还可以有null)
select * from emp e where e.empno not in (7369,7788,7782,null);
----(null和任何东西作运算都返回false，要用is null和is not null来比较)
select * from emp e where  e.empno !=7369 and  e.empno !=7782  and e.empno!=null;

                          --- true   and   true and  true  and false  = false;




-----课堂练习

--查询员工表中工资最高的前三名
----  伪列 rownum ,rowid
------先查询 后排序
-------(这个是先生成表再根据sal进行降序排序，因此rownum不是从小到大的，要想办法改成先对薪水进行排序再加序号rownum)
select e.* ,rownum  from emp e order by sal desc;

select t.*,rownum from (select * from emp order by sal desc) t  where rownum <4;


--------取 4-6 -------
-----rownum  不支持 大于!!!!!!!!!!!!!!!!!!!  -----------------
--------(注意，即使是在括号里面，即使使用了表的别名tt，依然必须使用rownum的别名rm而不能是rm)
--------(还要注意，如果不用前面的select * from，即使用了rownum的别名rm也不行，依然会报错)
---------(总之，既要加一个select * from()又要使用rownum的别名而不能用rownum本身)
select * from (select t.*,rownum rm from (select * from emp order by sal desc) t)  tt 
where tt.rm <7 and tt.rm >3;

--------不常用-------------
select * from (select row_number() over(order by sal desc) rm , e.* from emp e) t
where t.rm<7 and t.rm >3;

--------(对于分页和索引)-------
-------oracle 通过 rownum------
-------mysql       limit-----------
-------sqlserver    top----------

--找到员工表中薪水大于本部门平均薪水的员工

select avg(e.sal),e.deptno from emp e  group by e.deptno;
----------(emp p表才是要找的数据，t表只是辅助，因为最终找的是员工信息，而emp表包含未筛选过的所有信息)
select p.* from emp p  ,(select avg(e.sal) x,e.deptno y from emp e  group by e.deptno) t
where p.sal > t.x  and p.deptno=t.y;


--统计每年入职的员工个数
----select count(*),hiredate from emp group by hiredate;的话就是按照日分组而不是按照年分组。要按照年的话要先用to_char()方法转换成字符串
select count(*),to_char(e.hiredate,'yyyy') from  emp  e group by  to_char(e.hiredate,'yyyy');


-----行转列----------------
----1(from后面的表格求出了每一年份的入职员工数，sum(x)求出了历年入职的员工总数
select  sum(x)  "Total"
from (select count(*) x ,to_char(e.hiredate,'yyyy') y from  emp  e group by  to_char(e.hiredate,'yyyy')) t

----2(from后面的表格有两列，分别是该年份新入职的员工总数和年份)(t.y是年份字符串)
select case t.y 
      when '1980' then t.x
        end  "1980"
 from (select count(*) x ,to_char(e.hiredate,'yyyy') y from  emp  e group by  to_char(e.hiredate,'yyyy')) t


-----3(end后面的1980是别名)
select sum(case t.y 
      when '1980' then t.x
        end)  "1980"
 from (select count(*) x ,to_char(e.hiredate,'yyyy') y from  emp  e group by  to_char(e.hiredate,'yyyy')) t


-------4.(这是完整的行转列，上面的都是思维过程(或思路)，而不是最终的答案)(when表示的是列名，then表示的是列的数据)
select
sum(x)  "Total",
sum(case t.y 
  when '1980' then t.x
	end)  "1980",
sum(case t.y 
  when '1981' then t.x
	end)  "1981",
sum(case t.y 
  when '1982' then t.x
    end)  "1982",
sum(case t.y 
  hen '1987' then t.x
    end)  "1987"    
 from (select count(*) x ,to_char(e.hiredate,'yyyy') y from  emp  e group by  to_char(e.hiredate,'yyyy')) t
 
 -------------4更清晰版本的行转列：(自己写的)
 select
sum(t.x) "Total",
sum(case t.y when '1980' then t.x end) "1980",
sum(case t.y when '1981' then t.x end) "1981",
sum(case t.y when '1982' then t.x end) "1982",
sum(case t.y when '1987' then t.x end) "1987"
from (select count(*) x,to_char(e.hiredate,'yyyy') y from emp e group by to_char(e.hiredate,'yyyy')) t;

--- oracle特有  decode
-------exists
select * from emp  where exists (select * from dept where deptno=1);



-----这句话是什么意思？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
select d.* from dept d where exists (select e.* from emp e where d.deptno=e.deptno);
-------(对dept d的行进行筛选，当然保留所有列)(这个查询语句查询的是员工中存在的部门。过滤掉不存在的部门，是左表大右表小)
select * from dept d where d.deptno in (select distinct e.deptno from emp e);

----左表大 （数据量多） 右表小 （数据量少） 的时候  in效率高
----左表小 （数据量少） 右表大 （数据量多） 的时候  exists效率高



--集合运算(各种集合运算)
--范例：工资大于1500，或者是20号部门下的员工(下面有两种写法)
select * from emp e where e.sal >1500  or  e.deptno=20;

select * from emp e where e.sal >1500 
union  ---合并结果集 去重复
select * from emp e where e.deptno=20;


select * from emp e where e.sal >1500 
union all   ----结果集强制合并  不去重复(区分union和union all即可)
select * from emp e where e.deptno=20;



--范例：工资大于1500，并且是20号部门下的员工(下面有两种写法)(intersect:相交，交叉)

select * from emp  e where e.sal >1500 and e.deptno=20;

select * from emp  e where e.sal >1500
intersect
select * from emp  e where e.deptno=20;



--范例：1981年入职的普通员工（不包括总裁和经理）(minus指的是除。。。。之外)

select * from emp e where to_char(e.hiredate,'yyyy')='1981'
minus
select * from emp p  where p.job in ('MANAGER','PRESIDENT');





-----------------集合补充----------------
------只要  保证  查询 列的数量 列的类型一致 就可以做集合操作(说明了集合操作的条件和前提是列的数量和类型一致)(minus:减去)

select e.empno ,e.ename from emp  e
union
select d.deptno,d.dname from dept d;





