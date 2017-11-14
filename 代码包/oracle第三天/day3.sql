
-----sql ִ��˳��(��Ϊwhere��ִ����groupǰ�����Է���֮���ɸѡ������having.ע���Ƿ���֮�����������֮��)
FROM�C>JOIN�C>WHERE�C>GROUP�C>HAVING�C>DISTINCT�C>ORDER�C>TOP(limit);



7������ְ���ڳ���10����˼�н10%��
select e.empno,e.ename,e.sal*1.1 "��н��нˮ" from emp e 
where (months_between(sysdate,e.hiredate)/12)>10;

----(��ȥ120����)
select e.empno,e.ename,e.sal*1.1 "��н��нˮ" from emp e 
where e.hiredate < add_months(sysdate ,-120);



-----select * from ��  where  �� =xxx*aaa��
-----select * from ��  where  ��*aaa =xxx��


------mysql     

-----�������ݿ�
----������
----crud



--- oracle
---��ռ�
---�����û�
---���û���Ȩ��
---������
---crud


----------�л���dba �û�     system---------------


------------������ռ� (3��)------------------
create tablespace itheimaspace    -----��ռ�����
datafile 'c:/itheimaspace.dbf'    -----���ݿ��ļ�
size  100M                        -----��ռ��С
autoextend on                     -----���Զ�����
next 10M                          -----ÿ����������


---------------�����û�------------------
create user crm     ---�û���
identified by crm   ----����
default tablespace itheimaspace



-------------��ѯ��ǰ�û�Ȩ��------
select * from session_privs;


------��Ȩ�� (3��)--------------
grant  dba to crm;

-------��������

---��ֵ
number(3)   999
-----(��һ���������ܹ���λ���ڶ��������Ǽ�λС��)
number(3,2) 9.99


---�ַ�
char(10)      ---�����ַ�   aaa    10 
varchar(10)   ---�ɱ��ַ�   aaa    3
varchar2(10)  ---ʹ��Ч����varchar һ��    �Ƽ�ʹ��varchar2 

---����
date  ���ں�ʱ��
timestamp  ʱ���   ���9λ

---���ֶ�
long   2G
clob   4G
blob   4G


-----������ ��4�ǣ�
create table person(
       pid number(10),
       pname varchar2(30)
)


select * from person;


------�޸ı�(3��)
-----(������ʱ����column�ؼ���)
alter table person add sex varchar2(8);
------(ע���column�ؼ���)
alter table person rename column  sex to gender;
-----0 Ů  1��
alter table person modify gender number(1);
alter table person drop column gender;



-----ɾ����
drop table person;




----5�� +
-----��������
----ȫ�в���
insert into person  values(1,'����',1);

----ѡ�в���(Ҳ�������������null)

insert into person (pid,pname) values(2,'С5');



----���� (�����ش�)(ע����±��ʱ��û�йؼ���table)
update person set gender=0 where pid=1;


----ɾ��(�����ش�)
delete from person where pid=2;

truncate table person; ɾ����������,���ɻع�



insert into person  values(1,'���¸�',1);


select * from person;


---------Լ�� (4��)----------
----����   �ǿ�    Ψһ    ���  ���  ------
create table person(
       pid number(10) primary key,
       pname varchar2(30) not null,
       phone varchar2(20) unique,
       gender number(1)  check(gender in (0,1))
)

insert into person  values(1,'���¸�','1333333333',1);


--------�Զ���Լ��(�Զ������˼���Զ���Լ������)��3�ǣ���ע�⣬not null�����Զ���Լ����-------
create table person(
       pid number(10) ,
       pname varchar2(30) not null,
       phone varchar2(20) ,
       gender number(1)  ,
       constraint pk_person primary key(pid),
       constraint uk_phone unique(phone),
       constraint gk_gender check(gender in (0,1))
)



--------------���Լ��----------------
--------������ ������(������Ĵӱ����ʹ��)

create table orders(
       ooid  number(10) primary key,
       ootext varchar2(20)
)


-------����������ӱ�(�����д������ȷ��)
--------ע����references������reference
create  table order_item(
        oiid number(10) primary key,
        oitext varchar2(20),
        ooid number(10),
-----references�ؼ��ֺ����Ǳ���(����)
        constraint fk_order foreign key(ooid) references orders(ooid)
)

--------�����д���Ǵ���ġ����밴��������ʦд�ĵ����г��Զ���������
create table order_item(
oiid number(10) primary key,
oitext varchar2(20),
ooid number(10) foreign key references orders(ooid)
);


------------���ӱ� 
------����������� ��Ҫ�Ȳ��� ���� �ٲ���ӱ�
------���Ҫɾ������  ��Ҫ��ɾ���ӱ� ��ɾ������
insert into orders values(1,'618������');

insert into order_item values (1,'�޼����mx518',1);

select * from orders;
select * from order_item;

------ɾ�����ӱ�(����˵��ɾ�����ӱ��еĲ�������)
delete from order_item where ooid=1;
delete from orders where ooid=1;



-----�ύ����
commit;

select * from person;

----���񱣴�� (3��)(rollback�ǻع���commit֮�����ǰ��λ��)(����rollback��rollback to)

update person set pname='������' where  pid=1;
savepoint a;
update person set pname='�ձ�' where  pid=1;
savepoint b;
update person set pname='�ź���' where  pid=1;
savepoint c;
update person set pname='�ɺ���' where  pid=1;
savepoint d;

rollback to c;


---------------ͬ���(3��)-----------------------

select * from scott.emp;

create synonym sp for scott.emp;

create public synonym sp1 for scott.emp;

select * from sp1;


-------------���Ʊ�(�൱�ڸ�����emp��)------------(scott.emp�ĸ�ʽ���û���.���)
-------------as���治������
create table v_emp as select * from scott.emp;


-----------------��ͼ-------------------
----------��װ��һ��sql���Ķ���----------------(���Ʊ�ʹ�����ͼ������as+sql���)

create view ��ͼ������  as sql���;

--------�򻯸��Ӳ�ѯ
create view v1 as select * from v_emp;

select * from v1;
--------����������Ϣ


create view v2 as select empno,ename,job from v_emp;
--------����ͼ�Ĳ�ѯ�Ͷ�ʵ���Ĳ�ѯ��һ���ģ�ֻ��ǿ�Ҳ��Ƽ��޸���ͼ
select * from v2;


create view v3 as select empno eno,ename ee,job jj from v_emp;
select * from v3;



update v1  set  ename='³��' where empno=7369;
------------------��ͼ���洢����-------------

-----------ֻ����ͼ(ֻ���ڴ�����ͨ��ͼ�������with read only)----------------
create view v4 as select * from v_emp with read only;
-----�޸���ͼ���Ƽ�
----update v4  set  ename='³��1' where empno=7369;



------------------����   index(Ŀ¼) -----------------------
--------------������  �����߲�ѯЧ�ʵ����ݿ����-----------------

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



-----��������--------(on����Ľṹ�Ǳ�(��))
create index stu_index  on stu(stuname);


----0.047
select * from stu where stuname = 'abcd4004000';

-------���ݿ�������ѯʱ  ��������--------


-------������Ӱ����� �޸�  ɾ��(Ҳ���ǳ��˲�ѯ֮�ⶼӰ��)--------------



-------------��˭��������    1.���ѯ�ĸ���Զ������ɾ�ĵĸ���     2. ����������where ��������ֶ�
----3.Ψһ�Խ�ǿ���ֶ�   


----in , or  , like  '%%'  , �ֶ�*xxx(��˼�ǳ˷�)   ��������

-------like  'M%'  ----������(���Կ�������=M��like 'M%'֮������Ķ���������)


where  stuid=xxx  and stuname=xxx;

------��������
create index �������� on��(�У�

------��������(��֮���ö��ż����������֮������˳��ģ�˳�򲻶ԵĻ�Ҳ����������)
create index �������� on��(��1,��2.������

select * from ��  where  ��1=xxx and ��2=xxx��----Ч�ʸ�  ������

select * from ��  where  ��2=xxx and ��1=xxx��-----Ч�ʵ�  ��������



--------���� Լ��   ΨһԼ��   �Զ��ᴴ������(ע�����Զ���������)
--------(���Կ�����ΨһԼ�����Զ�������������Ϊ����Լ��Ҳ��һ�������ΨһԼ��)
--------hash    btree    

------���ݽṹ   �㷨����--------------

  

------------------����------------------
create sequence seq;

------��ȡ���е���һ��ֵ---
select seq.nextval from dual;
-----��ȡ���еĵ�ǰֵ----
select seq.currval from dual;

select * from person;
------���������ڶ������Ĳ�ѯ
insert into person values(seq.nextval,'������','15888888887',1);
commit;






