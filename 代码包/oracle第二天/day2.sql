------����ѯ-------------
------��ѯ Ա����Ϣ�Լ�Ա���Ĳ�����Ϣ--------------------

---------14  *  dept  4    =56 ;
select * from emp e, dept d;

-------------�ѿ�����   û��ʵ������ ��������� ��˵��sql д��������

----��ֵ����
----������ ����ʽ��
select * from emp e, dept d
where  e.deptno=d.deptno ;

----------ע�����inner join֮������where�����on
select * from  emp e inner join dept d  on e.deptno=d.deptno ;


----------�ظ��������Ļ�Ҫ���ϱ�����������Ҳ���Լ�������
select e.empno,d.deptno from emp e, dept d
where  e.deptno=d.deptno;

----����ֵ���� (�˽�)
select * from emp e, dept d
where  e.deptno!=d.deptno;


--��������ѯ��ÿ��Ա�����ϼ��쵼 (Ա����ţ�Ա���������쵼��ţ��쵼����)(������ͬ�ı����inner join ������)

select e1.empno "Ա�����",e1.ename "Ա������",e2.empno "�쵼���",e2.ename "�쵼����" 
from emp e1,emp e2 
where e1.mgr=e2.empno;


--����: ����һ�����ӵĻ����ϲ�ѯ��Ա���Ĳ�������(ע���sql��������֮����and����)

select e1.empno "Ա�����",e1.ename "Ա������", e1.deptno "Ա�����ű��", d.dname "Ա���Ĳ�������",
e2.empno "�쵼���",e2.ename "�쵼����" 
from emp e1,emp e2  ,dept d
where e1.mgr=e2.empno
and e1.deptno=d.deptno
;

--��������ѯ��ÿ��Ա����ţ��������������ƣ����ʵȼ��������ϼ��쵼�����������ʵȼ�
--(ע��and������Լ�between and���Ҳ��ü�����)
--(ÿ�����ֶ�ռһ�п������������)(ע�⣬���ʺ͹��ʵȼ���Ҫ��ѯ)
--(ע�⣬e1��Ա����e2���쵼������salgrade���ҲҪ�ֳ�s��s2�ֱ��ӦԱ�����쵼)
--(������Ȼֻ��������ͬ���ı����������ĸ������ϲ�ѯ)
--(����͹���losal����߹���hisal�õ���Ӧ�Ĺ��ʵȼ�grade)

select e1.empno "Ա�����",e1.ename "Ա������", e1.deptno "Ա�����ű��",
 d.dname "Ա���Ĳ�������", e1.sal "Ա������", s.grade "Ա�����ʵȼ�",
e2.empno "�쵼���",e2.ename "�쵼����" ,e2.sal "�쵼����" ,s2.grade "�쵼���ʵȼ�"
from emp e1,emp e2  ,dept d ,salgrade s,salgrade s2
where e1.mgr=e2.empno
and e1.deptno=d.deptno
and  e1.sal between s.losal and s.hisal
and e2.sal between s2.losal and s2.hisal;



--��������ѯ������Ա�����ϼ��쵼(��Ա��Ϊ׼)
--��������ѯ�����еĲ����µ�Ա����Ҫ���û��Ա���Ĳ���Ҳչʾ����(�Բ���Ϊ׼)
------������

------����  ���� -----
-----ȫ����
--��������������������Ч����һ����
--(��������������ߵı�Ϊ���������Ա����ź�Ա������Ϊ����Ҳ������Ϊ���ı��е���Ϊ����Ҳ����û���쵼��Ա��Ҳ���г���)
--(��Ҫ���Ӳ�ѯ��ԭ����ͬһ�ű���ֻ���쵼���û���쵼���������Ҫ���ϲ�ѯ)
--(������������������Ӳ�ѯ���ֻ��emp e1 left join  emp e2��emp e2 right join emp e1��������������û���κβ�ͬ)
--(ע�����ű��ж���ȡ��Ա������һ�ű�����쵼����������һ�ű���һ�ű�����쵼�ɴ���ȫû����)
select e1.empno "Ա�����",e1.ename "Ա������",e2.empno "�쵼���",e2.ename "�쵼����" 
from  emp e1 left join  emp e2 on e1.mgr=e2.empno;

select e1.empno "Ա�����",e1.ename "Ա������",e2.empno "�쵼���",e2.ename "�쵼����" 
from emp e2 right join emp e1  on e1.mgr = e2.empno;


-------oracle ����������------------
--(�Ӻ�����߾����������ӣ��Ӻ����ұ߾�����������)
--(������ͨ�õ������ӷ����������ǲ���д��left join��rightjoin������on�ؼ�����ȻҲ�ͻָ�����where�ؼ���)
select e1.empno "Ա�����",e1.ename "Ա������",e2.empno "�쵼���",e2.ename "�쵼����" 
from emp e1,emp e2 
where e1.mgr=e2.empno(+);  



-----ȫ�������(���쵼Ϊ������ʹ��û��Ա�����쵼Ҳ���г����ģ�û���쵼���쵼Ӧ���������쵼)
select e1.empno "Ա�����",e1.ename "Ա������",e2.empno "�쵼���",e2.ename "�쵼����" 
from  emp e2 left join  emp e1 on e1.mgr=e2.empno;







-------------------
--��ѯ��SCOTT���ʸߵ�Ա��
----�Ӳ�ѯ    ֧�� һ��һ��   һ�ж���   ���ж���
select sal from emp  where ename='SCOTT';
--��һ�������˱���
select e.* from emp  e where e.sal > (select sal from emp  where ename='SCOTT');
--�����������˱���
select e.* from emp  e where e.sal > (select p.sal from emp p where p.ename='SCOTT');
--��Ȼ�����ϲ�ѯ����ֻ��e1�е���
select e1.* from emp e1 ,emp e2 where e2.ename='SCOTT' and e1.sal >e2.sal;


--��ѯְλ�Ǿ����ҹ��ʱ�7782��Ա���ߵ�Ա��
select sal from emp where empno=7782;
select * from emp e where e.job='MANAGER' and e.sal > (select sal from emp where empno=7782);

--��ѯ������͵�Ա��(���Ҫ�Ȳ�ѯ����͹���)

select min(sal) from emp;
select * from emp e where e.sal = (select min(sal) from emp);


--��ѯ������͹��ʴ���30�Ų�����͹��ʵĽ��
--��ÿ�����ѯһ����͹��ʣ������м������ž��м���(Ҳ�����м���������͹���)
select min(sal) from  emp group by deptno;
--30�Ų��ŵ���͹���
select min(sal) from emp where deptno=30;
--group by ����ֻ����having������where
select min(sal) from  emp group by deptno  having min(sal) >(select min(sal) from emp where deptno=30);


--��ѯ����scottͬ���Ų���ְͬλ��Ա��

select deptno from emp  where ename='SCOTT';

select     job from emp  where ename='SCOTT';

select * from emp e where  e.deptno=(select deptno from emp  where ename='SCOTT') 
and e.job=(select   job from emp  where ename='SCOTT');
------(�����������)
select * from emp e where ( e.deptno ,e.job ) = (select deptno,job from emp  where ename='SCOTT');


--��ѯÿ�����ŵ���͹��ʺ���͹��ʵĹ�Ա�Ͳ�������(ע��deptno�ǲ��ű�Ŷ����ǲ�������)(���Կ�����ѯ�����Ľ��������(����)�ǲ��ŵ�����)
--(��͹����Լ��ò��ŵ�����Ӧ�����ȱ������������Ϊһ���ӱ�)
select  min(sal),deptno from emp  group by deptno;

-----(ע�������������������Ҫ���ϸ������ڵı�ı�����ǰ׺)
----(dept����Ϊ�˲�ѯ�������ƣ�empt����Ϊ�˲�ѯ�ù�Ա��������Ϣ)
----(empt���а���Ա���Ĺ��ʣ������ȻҲ�Ͱ����˸ò���Ա������͹�����Ϣ)
----(������������ʱ�����Ľ������������ᳬ���⼸������������С�ı������)
select e.* ,d.dname
from (select  min(sal) x ,deptno y from emp  group by deptno) t ,emp e ,dept d
where e.sal=t.x and e.deptno=t.y
and e.deptno=d.deptno
;

--��ѯ�������쵼��Ա��(��ѯ��Ա����������Ϣ��Ҳ����emp���������)��������
---��ѯ�쵼�ı��

------�����Ӳ�ѯʱ��Ҫ��ȥ ��ֵ
----(��ѯ���������쵼�ı��)
----(empno��Ա�����)
----where mgr is not null�ų���mgr����Ϊnull�����
select distinct mgr from emp;

select * from emp e where e.empno not in (select distinct mgr from emp where mgr is not null);




----------------------------------
select * from emp e where  e.empno=7369  or e.empno=7788  or  e.empno= 7782  or e.empno= null;
                        ----    true   or   true  or true   or false  =true;
select * from emp e where e.empno in (7369,7788,7782 ,null);
-------(ע�⼯�����滹������null)
select * from emp e where e.empno not in (7369,7788,7782,null);
----(null���κζ��������㶼����false��Ҫ��is null��is not null���Ƚ�)
select * from emp e where  e.empno !=7369 and  e.empno !=7782  and e.empno!=null;

                          --- true   and   true and  true  and false  = false;




-----������ϰ

--��ѯԱ�����й�����ߵ�ǰ����
----  α�� rownum ,rowid
------�Ȳ�ѯ ������
-------(����������ɱ��ٸ���sal���н����������rownum���Ǵ�С����ģ�Ҫ��취�ĳ��ȶ�нˮ���������ټ����rownum)
select e.* ,rownum  from emp e order by sal desc;

select t.*,rownum from (select * from emp order by sal desc) t  where rownum <4;


--------ȡ 4-6 -------
-----rownum  ��֧�� ����!!!!!!!!!!!!!!!!!!!  -----------------
--------(ע�⣬��ʹ�����������棬��ʹʹ���˱�ı���tt����Ȼ����ʹ��rownum�ı���rm��������rm)
--------(��Ҫע�⣬�������ǰ���select * from����ʹ����rownum�ı���rmҲ���У���Ȼ�ᱨ��)
---------(��֮����Ҫ��һ��select * from()��Ҫʹ��rownum�ı�����������rownum����)
select * from (select t.*,rownum rm from (select * from emp order by sal desc) t)  tt 
where tt.rm <7 and tt.rm >3;

--------������-------------
select * from (select row_number() over(order by sal desc) rm , e.* from emp e) t
where t.rm<7 and t.rm >3;

--------(���ڷ�ҳ������)-------
-------oracle ͨ�� rownum------
-------mysql       limit-----------
-------sqlserver    top----------

--�ҵ�Ա������нˮ���ڱ�����ƽ��нˮ��Ա��

select avg(e.sal),e.deptno from emp e  group by e.deptno;
----------(emp p�����Ҫ�ҵ����ݣ�t��ֻ�Ǹ�������Ϊ�����ҵ���Ա����Ϣ����emp�����δɸѡ����������Ϣ)
select p.* from emp p  ,(select avg(e.sal) x,e.deptno y from emp e  group by e.deptno) t
where p.sal > t.x  and p.deptno=t.y;


--ͳ��ÿ����ְ��Ա������
----select count(*),hiredate from emp group by hiredate;�Ļ����ǰ����շ�������ǰ�������顣Ҫ������Ļ�Ҫ����to_char()����ת�����ַ���
select count(*),to_char(e.hiredate,'yyyy') from  emp  e group by  to_char(e.hiredate,'yyyy');


-----��ת��----------------
----1(from����ı�������ÿһ��ݵ���ְԱ������sum(x)�����������ְ��Ա������
select  sum(x)  "Total"
from (select count(*) x ,to_char(e.hiredate,'yyyy') y from  emp  e group by  to_char(e.hiredate,'yyyy')) t

----2(from����ı�������У��ֱ��Ǹ��������ְ��Ա�����������)(t.y������ַ���)
select case t.y 
      when '1980' then t.x
        end  "1980"
 from (select count(*) x ,to_char(e.hiredate,'yyyy') y from  emp  e group by  to_char(e.hiredate,'yyyy')) t


-----3(end�����1980�Ǳ���)
select sum(case t.y 
      when '1980' then t.x
        end)  "1980"
 from (select count(*) x ,to_char(e.hiredate,'yyyy') y from  emp  e group by  to_char(e.hiredate,'yyyy')) t


-------4.(������������ת�У�����Ķ���˼ά����(��˼·)�����������յĴ�)(when��ʾ����������then��ʾ�����е�����)
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
 
 -------------4�������汾����ת�У�(�Լ�д��)
 select
sum(t.x) "Total",
sum(case t.y when '1980' then t.x end) "1980",
sum(case t.y when '1981' then t.x end) "1981",
sum(case t.y when '1982' then t.x end) "1982",
sum(case t.y when '1987' then t.x end) "1987"
from (select count(*) x,to_char(e.hiredate,'yyyy') y from emp e group by to_char(e.hiredate,'yyyy')) t;

--- oracle����  decode
-------exists
select * from emp  where exists (select * from dept where deptno=1);



-----��仰��ʲô��˼��������������������������������������������������������������������������������������������������������������
select d.* from dept d where exists (select e.* from emp e where d.deptno=e.deptno);
-------(��dept d���н���ɸѡ����Ȼ����������)(�����ѯ����ѯ����Ա���д��ڵĲ��š����˵������ڵĲ��ţ��������ұ�С)
select * from dept d where d.deptno in (select distinct e.deptno from emp e);

----���� ���������ࣩ �ұ�С ���������٣� ��ʱ��  inЧ�ʸ�
----���С ���������٣� �ұ�� ���������ࣩ ��ʱ��  existsЧ�ʸ�



--��������(���ּ�������)
--���������ʴ���1500��������20�Ų����µ�Ա��(����������д��)
select * from emp e where e.sal >1500  or  e.deptno=20;

select * from emp e where e.sal >1500 
union  ---�ϲ������ ȥ�ظ�
select * from emp e where e.deptno=20;


select * from emp e where e.sal >1500 
union all   ----�����ǿ�ƺϲ�  ��ȥ�ظ�(����union��union all����)
select * from emp e where e.deptno=20;



--���������ʴ���1500��������20�Ų����µ�Ա��(����������д��)(intersect:�ཻ������)

select * from emp  e where e.sal >1500 and e.deptno=20;

select * from emp  e where e.sal >1500
intersect
select * from emp  e where e.deptno=20;



--������1981����ְ����ͨԱ�����������ܲú;���(minusָ���ǳ���������֮��)

select * from emp e where to_char(e.hiredate,'yyyy')='1981'
minus
select * from emp p  where p.job in ('MANAGER','PRESIDENT');





-----------------���ϲ���----------------
------ֻҪ  ��֤  ��ѯ �е����� �е�����һ�� �Ϳ��������ϲ���(˵���˼��ϲ�����������ǰ�����е�����������һ��)(minus:��ȥ)

select e.empno ,e.ename from emp  e
union
select d.deptno,d.dname from dept d;





