---scott    hr------
----tiger--------

-------------�����û� ---------------
alter user scott account unlock;
------------�޸�����--------------
alter user scott identified by tiger;


----------------------�л��û�-------------------------


----dept     ���� ��

---deptno  ���ű��
---dname   ��������
---loc     ���ų���

------emp     Ա����Ϣ

---empno   Ա�����
---ename   Ա������
---job     ְλ
---mgr     �쵼���
---hireate  ��ְʱ��
---sal      ����
---comm     ����
---dpetno   ���ű��

----tree     ���νṹ


-----------salgrade   ���ʵȼ�
---grade   ���ʼ���
---losal   ����Ĺ�������
---hisal   ����Ĺ�������



---------------��ѯһ��Ա����Ϣ---------------
select * from emp ;
select empno ,ename ,job from emp ;

-----""  ֻ���û����α��� �������õ�����
select empno as eno ,ename  ee,job  "ְλ" from emp ;

--???-----

/**
 ��������������
1.�鿴�������˱���
select userenv('language') from dual;
��ʵ�ʲ鵽�Ľ��Ϊ:AMERICAN_AMERICA.ZHS16GBK
2.ִ����� select * from V$NLS_PARAMETERS 
�鿴��һ����PARAMETER����ΪNLS_LANGUAGE ��Ӧ��VALUE�����Ƿ�͵�һ���õ���ֵһ����
������ǣ���Ҫ���û�������.
����PLSQL�ͻ���ʹ�õı���ͷ������˱��벻һ��,��������ʱ�ͻ��������.
3.���û�������
�����->����->�߼�ϵͳ����->��������->�½�
���ñ�����:NLS_LANG,����ֵ:��1���鵽��ֵ�� �ҵ���	AMERICAN_AMERICA.ZHS16GBK
4.��������PLSQL,������������
*/



----��ѯһ�� ��н-------------
----sql���֧����������
select sal *13 from emp ;


-----�����ַ�
select concat(empno,ename) from emp ;

select empno|| '====' ||ename from emp ;


-----ȥ�ظ�
select distinct job from emp;


--������ѯ
--��ѯ�û����Ϊ 7369��Ա��

select * from emp  e where e.empno=7369;


--��ѯ���� comm��Ϊ�յ�Ա��
---null ������   null ������null  ��null ��˭����˭�ͱ��null
select * from emp e where  e.comm  is not null;

select e.sal *16 + nvl(e.comm,0),e.comm  from emp e; 


--��ѯ����commΪ�ղ��ҹ��ʴ��� 1500
select * from emp e where e.comm is null and e.sal>1500;



--��ѯ����commΪ�ղ��ҹ��ʲ����� 1500

select * from emp e where e.comm is null and  e.sal <=1500;
select * from emp e where e.comm is null and  not(e.sal >1500);


--��ѯ����commmΪ�ջ��߹��ʴ��� 1500
select * from emp e where e.comm is null  or  e.sal >1500;


--��Χ��ѯ
--��ѯ���ʴ���1500 ����С��3000 

select * from emp e where e.sal >1500 and e.sal<3000;
------between  �����ٽ��
select * from emp e where e.sal between 1500 and 3000;



--��ѯԱ����� ��  7369  7788  7654 ��Ա��
select * from emp e where e.empno=7369 or e.empno=7788 or e.empno=7654;

select * from emp e where e.empno in (7369,7788,7654);

--��ѯԱ��������  SMTH   MARTIN   SCOTT��ע�ⲻ�ܲ������Ŷ��ұ���ӵ�����

select * from emp e where e.ename  in ('SMITH','MARTIN','SCOTT');



--��ѯԱ����Ų�����  7369��Ա��

select * from emp e where e.empno !=7369;
select * from emp e where e.empno <>7369;



--����  

--����������
---����
select * from emp e order by e.sal;
select * from emp e order by e.sal asc;
---����
select * from emp e order by e.sal desc;


--����������  ����

select * from emp e order by e.comm desc  nulls last;

select * from emp e order by e.comm desc  nulls first;



--ģ����ѯ

--��ѯԱ�������� ��M��

select * from emp e where e.ename like  '%M%';

------oracle  �������ִ�Сд �� �ؼ��ֲ�����
select * from emp e where e.ename like  '%m%';
SELECT * FROM EMP E WHERE E.ENAME LIKE  '%M%';

--��ѯԱ�������ڶ�����ĸ��M��Ա��
select * from emp e where e.ename like  '_M%';

--��ѯԱ��������������ĸ��M��Ա��
select * from emp e where e.ename like  '__M%';


------��ѯԱ��������_��Ա��������r����ת���ַ����ǵü����ţ��ǵüӰٷֺ�
select * from emp e where e.ename like '%r_%'  escape 'r';



--��ѯÿ��Ա�������ж���λ
select length(ename),ename from emp;

----------���к���    ����ִ����  �������ݻ��Ǽ�������(Ҳ���ǲ��ϲ�) ---------------

----�ַ�����
---Сд
select lower( ename) from emp ;

----��д
select upper( ename) from emp ;

------------�������䣬չʾ�涨���ݼ���
select upper('abcdef') from emp ;

-----α��  ���  ������ȫ�﷨(dual�������)(ֻ��һ��һ��)
select upper('abcdef') from dual;

----�ַ�����
select concat( concat('abc','df'),'bvv') from dual;

-----ȥ�ո� 
select trim('  agc  vvv    ') from dual;
----�滻
select replace('  agc  vvv    ',' ','') from dual;


----��ֵ����(���Ǻ���ֵ�йص�)
round
----����С��  ��������(�ڶ���������ʾҪȡ��С��λ��)
select  round(3.141592653589793238462643383279,4)   from dual;

trunc
----����С��   �ض�
select  trunc(3.141592653589793238462643383279,4)   from dual;


mod
----ȡģȡ��------
select mod (4,3) from dual;



-----���ں���
---��ȡ��ǰ����
select sysdate from dual;


-----���ڿ��Խ�����������
select sysdate +1 from dual; 

-----��ȡ��������
select sysdate +7 from dual; 


-----��ȡ��������
select  add_months( sysdate ,1) from dual; 

-----��ȡ��������
select  add_months( sysdate ,12) from dual; 


-----��Ա����ְ������
select round(sysdate - e.hiredate) from emp e;

-----��Ա����ְ������
select round((sysdate - e.hiredate)/7) from emp e;

-----��Ա����ְ������    ע�������������������������ö��ż���������ü��ż��
select round(months_between(sysdate,e.hiredate))  from emp e; 



-----ת������

---�ַ�ת����  ע����to_number������to_num
select '123' ,to_number('123') from dual;
---����ת�ַ�
select 123,to_char(123) from dual;

select * from emp e where e.empno=7369;
select * from emp e where e.empno='7369';



---����ת�ַ�  ǰ�����mm������mi����Ϊoracle�����ִ�Сд���ڶ���������Ҫת���ɵ��ַ����Ĺ涨��ʽ��ǰ����-������м��ÿո�����������:���
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual;


---�ַ�ת����  �ڶ���������������ַ��������صĹ��򣬷���ϵͳ���ַ���������Ӧ����Ľ����ת��
select to_date('2017-08-25 12:06:09','yyyy-mm-dd hh24:mi:ss') from dual;



-----ͨ�ú���
select e.sal * 12  + nvl(e.comm,0) from emp e; 



----���ʽ----   ��ת��   �����дwhere deptno=10�Ļ���Щ������������ename�ͻᶼ��ɿգ����ǻ��ǻ���ʾ����  �����Ǳ���������˫����
select case ename 
when 'CLARK' then  '³���ߺ�'
when 'KING' then  '����'
when 'MILLER' then  '������'    
end  "����"
from emp where deptno=10;


-----oracle ���б��ʽ(���Ƽ��ڹ�����ʹ��)(name��������⣬���Ǳ���)(���ö��ż�������÷ֱ�)
select decode( ename 
, 'CLARK' ,  '³���ߺ�'
, 'KING' ,  '����'
, 'MILLER' ,  '������'    
)  "����"
from emp where deptno=10;//��from��ʼ�ͺ������ͨ�÷�����һ����





----------���к������ۺϺ����� ����ִ����  �������ݱ��һ��(��Ӧ�ڵ��к���) --------------

count
----��ѯԱ������
select count(*) from emp ;
select count(1) from emp ;
select count(999) from emp ;
select count(0) from emp ;
select count('����') from emp ;
select count(empno) from emp ;


select count(comm) from emp ;


sum
---��ѯԱ�����ʺ�
select sum(sal) from emp ;

avg
---��ѯԱ������ƽ����
select avg(sal) from emp ;


max
---��ѯԱ����߹���
select max(sal) from emp ;
--min 
---��ѯԱ����͹���
select min(sal) from emp ;



-------���� ---------------
----�����Ų�ѯԱ������(deptno��ʾ���ǲ���)
-----���ʹ��group by ��select ��ֻ�ܳ��� �ۺϺ��� ���� group by  ��
select count(1) from emp group by deptno;

select count(1),deptno from emp group by deptno;


----�����Ų�ѯԱ������ ��ȡ��������5��
----having �Է����Ľ�����ٴι���
select count(1),deptno from emp group by deptno having count(1)>5;

----having  ��������group ֮��
----where   ��������group ֮ǰ 






