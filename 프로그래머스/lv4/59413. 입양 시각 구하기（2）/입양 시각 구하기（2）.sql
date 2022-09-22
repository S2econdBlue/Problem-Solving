with recursive timeTable as
(select 0 as hour union all select hour + 1 from timeTable where hour < 23)

select hour, count(animal_id) count
from timeTable
left join animal_outs on (hour =hour(datetime))
group by hour;