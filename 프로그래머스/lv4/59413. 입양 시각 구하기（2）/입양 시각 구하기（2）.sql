with recursive timeTable as (
select 0 as hours union all select hours + 1 from timeTable where hours < 23)

select hours as hour, count(animal_id) as count
from timeTable left join animal_outs on hours = hour(datetime)
group by hours
