--task1
--ошибки в расписании (фильмы накладываются друг на друга),
--отсортированные по возрастанию времени. Выводить надо колонки «фильм 1»,
--«время начала», «длительность», «фильм 2», «время начала», «длительность»;
with query1 as (
select s.id , m.movie_name, m.length, s.start_time,
s.start_time  + (m.length * interval '1 minute') as stop_time
from seances s
join movies m on m.id = s.movie_id
)
select q1.movie_name, q1.start_time, q1.length, q2.movie_name, q2.start_time, q2.length from query1 q1
join query1 q2 on q1.id != q2.id and q2.start_time > q1.start_time and q2.start_time < q1.stop_time
order by q2.start_time;

--task2
--перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
--Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
WITH query1 as (
select row_number () over (order by start_time) as row_num,
s.id , m.movie_name, m.length, s.start_time,
s.start_time  + (m.length * interval '1 minute') as stop_time
from seances s
join movies m on s.movie_id = m.id
)
select q1.movie_name, q1.start_time, q1.length, q2.start_time, (q2.start_time - q1.stop_time) as movie_interval
from query1 q1
join query1 q2
on q2.row_num = (q1.row_num + 1) and (q2.start_time - q1.stop_time) > (30 * interval '1 minute')
order by movie_interval desc;

--task3
--список фильмов, для каждого — с указанием общего числа посетителей за все время,
--среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
--Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
with query1 as (select m.movie_name , count(t.id) count_tickets, count(t.id)/count(s.id) avg_tickets, sum(s.price) sum_price from movies m
join seances s on m.id = s.movie_id
join tickets t on t.seance_id = s.id
group by m.movie_name
order by sum_price)
select * from query1 q1
union all
select 'Итого', sum(q2.count_tickets), avg(q2.avg_tickets), sum(q2.sum_price)
from query1 q2;

--task4
--число посетителей и кассовые сборы,
--сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00
--(сколько посетителей пришло с 9 до 15 часов и т.д.).
with query1 as (
select m.id movie_id, m.movie_name , s.start_time , s.price  from tickets t
join seances s on s.id = t.seance_id
join movies m on m.id = s.movie_id
where m.id = 1
)
select 'С 9 до 15 часов' show_time, q1.movie_id, q1.movie_name, count(q1.movie_id) count_visitors, sum(q1.price) from query1 q1
where q1.start_time::time between ('09:00:00') and ('15:00:00')
group by q1.movie_id, q1.movie_name
union all
select 'С 15 до 18 часов' show_time, q1.movie_id, q1.movie_name, count(q1.movie_id) count_visitors, sum(q1.price) from query1 q1
where q1.start_time::time between ('15:00:00') and ('18:00:00')
group by q1.movie_id, q1.movie_name
union all
select 'С 18 до 21 часа' show_time, q1.movie_id, q1.movie_name, count(q1.movie_id) count_visitors, sum(q1.price) from query1 q1
where q1.start_time::time between ('18:00:00') and ('21:00:00')
group by q1.movie_id, q1.movie_name
union all
select 'С 21 до 00 часов' show_time, q1.movie_id, q1.movie_name, count(q1.movie_id) count_visitors, sum(q1.price) from query1 q1
where q1.start_time::time between ('21:00:00') and ('00:00:00')
group by q1.movie_id, q1.movie_name
;