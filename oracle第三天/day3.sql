
-----sql 执行顺序(因为where的执行在group前面所以分组之后的筛选必须用having.注意是分组之后而不是排序之后)
FROM–>JOIN–>WHERE–>GROUP–>HAVING–>DISTINCT–>ORDER–>TOP(limit);



7、给任职日期超过10年的人加薪10%；
select e.empno,e.ename,e.sal*1.1 "加薪后薪水" from emp e 
where (months_between(sysdate,e.hiredate)/12)>10;

----(减去120个月)
select e.empno,e.ename,e.sal*1.1 "加薪后薪水" from emp e 
where e.hiredate < add_months(sysdate ,-120);



-----select * from 表  where  列 =xxx*aaa；
-----select * from 表  where  列*aaa =xxx；


------mysql     

-----创建数据库
----创建表
----crud



--- oracle
---表空间
---创建用户
---给用户赋权限
---创建表
---crud


----------切换到dba 用户     system---------------


------------创建表空间 (3星)------------------
create tablespace itheimaspace    -----表空间名称
datafile 'c:/itheimaspace.dbf'    -----数据库文件
size  100M                        -----表空间大小
autoextend on                     -----打开自动增长
next 10M                          -----每次增长多少


---------------创建用户------------------
create user crm     ---用户名
identified by crm   ----密码
default tablespace itheimaspace



-------------查询当前用户权限------
select * from session_privs;


------赋权限 (3星)--------------
grant  dba to crm;

-------数据类型

---数值
number(3)   999
-----(第一个参数是总共几位，第二个参数是几位小数)
number(3,2) 9.99


---字符
char(10)      ---定长字符   aaa    10 
varchar(10)   ---可变字符   aaa    3
varchar2(10)  ---使用效果和varchar 一样    推荐使用varchar2 

---日期
date  日期和时间
timestamp  时间戳   秒后9位

---大字段
long   2G
clob   4G
blob   4G


-----创建表 （4星）
create table person(
       pid number(10),
       pname varchar2(30)
)


select * from person;


------修改表(3星)
-----(增加列时不加column关键字)
alter table person add sex varchar2(8);
------(注意加column关键字)
alter table person rename column  sex to gender;
-----0 女  1男
alter table person modify gender number(1);
alter table person drop column gender;



-----删除表
drop table person;




----5星 +
-----插入数据
----全列插入
insert into person  values(1,'金星',1);

----选列插入(也就是其余的列是null)

insert into person (pid,pname) values(2,'小5');



----更新 (责任重大)(注意更新表的时候没有关键字table)
update person set gender=0 where pid=1;


----删除(责任重大)
delete from person where pid=2;

truncate table person; 删除表中数据,不可回滚



insert into person  values(1,'郭德纲',1);


select * from person;


---------约束 (4星)----------
----主键   非空    唯一    检查  外键  ------
create table person(
       pid number(10) primary key,
       pname varchar2(30) not null,
       phone varchar2(20) unique,
       gender number(1)  check(gender in (0,1))
)

insert into person  values(1,'郭德纲','1333333333',1);


--------自定义约束(自定义的意思是自定义约束名字)（3星）（注意，not null不能自定义约束）-------
create table person(
       pid number(10) ,
       pname varchar2(30) not null,
       phone varchar2(20) ,
       gender number(1)  ,
       constraint pk_person primary key(pid),
       constraint uk_phone unique(phone),
       constraint gk_gender check(gender in (0,1))
)



--------------外键约束----------------
--------订单表 （主表）(和下面的从表配合使用)

create table orders(
       ooid  number(10) primary key,
       ootext varchar2(20)
)


-------订单详情表（从表）(下面的写法是正确的)
--------注意是references而不是reference
create  table order_item(
        oiid number(10) primary key,
        oitext varchar2(20),
        ooid number(10),
-----references关键字后面是表名(列名)
        constraint fk_order foreign key(ooid) references orders(ooid)
)

--------下面的写法是错误的。必须按照上面老师写的单独列出自定义的外键：
create table order_item(
oiid number(10) primary key,
oitext varchar2(20),
ooid number(10) foreign key references orders(ooid)
);


------------主从表 
------如果插入数据 需要先插入 主表 再插入从表
------如果要删除数据  需要先删除从表 在删除主表
insert into orders values(1,'618鼠标大优');

insert into order_item values (1,'罗技鼠标mx518',1);

select * from orders;
select * from order_item;

------删除主从表(或者说是删除主从表中的部分内容)
delete from order_item where ooid=1;
delete from orders where ooid=1;



-----提交事务
commit;

select * from person;

----事务保存点 (3星)(rollback是回滚到commit之后的最前面位置)(区分rollback和rollback to)

update person set pname='岳云鹏' where  pid=1;
savepoint a;
update person set pname='烧饼' where  pid=1;
savepoint b;
update person set pname='张鹤伦' where  pid=1;
savepoint c;
update person set pname='郎核验' where  pid=1;
savepoint d;

rollback to c;


---------------同义词(3星)-----------------------

select * from scott.emp;

create synonym sp for scott.emp;

create public synonym sp1 for scott.emp;

select * from sp1;


-------------复制表(相当于复制了emp表)------------(scott.emp的格式是用户名.表格)
-------------as后面不加括号
create table v_emp as select * from scott.emp;


-----------------视图-------------------
----------封装了一段sql语句的对象----------------(复制表和创建视图都是用as+sql语句)

create view 视图的名称  as sql语句;

--------简化复杂查询
create view v1 as select * from v_emp;

select * from v1;
--------隐藏敏感信息


create view v2 as select empno,ename,job from v_emp;
--------对视图的查询和对实体表的查询是一样的，只是强烈不推荐修改视图
select * from v2;


create view v3 as select empno eno,ename ee,job jj from v_emp;
select * from v3;



update v1  set  ename='鲁班' where empno=7369;
------------------视图不存储数据-------------

-----------只读视图(只是在创建普通视图后面加了with read only)----------------
create view v4 as select * from v_emp with read only;
-----修改视图不推荐
----update v4  set  ename='鲁班1' where empno=7369;



------------------索引   index(目录) -----------------------
--------------索引是  大幅提高查询效率的数据库对象-----------------

create table stu (
       stuid number(10),
       stuname varchar2(20)
)


begin
  for i in 1..5000000
    loop
      insert into stu values(i,'abcd' || i);
    end loop;
    commit;
end;

----1.872
select * from stu where stuname = 'abcd4000000';



-----创建索引--------(on后面的结构是表(列))
create index stu_index  on stu(stuname);


----0.047
select * from stu where stuname = 'abcd4004000';

-------数据库在做查询时  索引优先--------


-------索引会影响插入 修改  删除(也就是除了查询之外都影响)--------------



-------------对谁创建索引    1.表查询的概率远高于增删改的概率     2. 经常出现在where 条件后的字段
----3.唯一性较强的字段   


----in , or  , like  '%%'  , 字段*xxx(意思是乘法)   不走索引

-------like  'M%'  ----走索引(可以看做除了=M和like 'M%'之外其余的都不走索引)


where  stuid=xxx  and stuname=xxx;

------单列索引
create index 索引名称 on表(列）

------复合索引(列之间用逗号间隔，而且列之间是有顺序的，顺序不对的话也不会走索引)
create index 索引名称 on表(列1,列2.。。）

select * from 表  where  列1=xxx and 列2=xxx；----效率高  走索引

select * from 表  where  列2=xxx and 列1=xxx；-----效率低  不走索引



--------主键 约束   唯一约束   自动会创建索引(注意是自动创建索引)
--------(可以看做是唯一约束会自动创建索引，因为主键约束也是一种特殊的唯一约束)
--------hash    btree    

------数据结构   算法分析--------------

  

------------------序列------------------
create sequence seq;

------获取队列的下一个值---
select seq.nextval from dual;
-----获取队列的当前值----
select seq.currval from dual;

select * from person;
------看来是用于对主键的查询
insert into person values(seq.nextval,'安琪拉','15888888887',1);
commit;






