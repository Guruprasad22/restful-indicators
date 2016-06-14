-- convert varchar timestamp to mysql date format and order by date
SELECT SYMBOL,SERIES,CLOSE,TIMESTAMP FROM MARKET.TICKER ORDER BY SYMBOL,SERIES,STR_TO_DATE(TIMESTAMP,'%d-%M-%Y') ASC

select count(distinct symbol,series) from market.ticker;

select count(distinct isin) from market.ticker;

select count(distinct symbol,series,isin) from market.ticker;

select * from market.ticker;

select symbol,series,close,timestamp,sma from market.indicator order by symbol,series,str_to_date(timestamp,'%d-%M-%Y') asc;

delete from market.ticker where symbol != "20MICRONS"

truncate table market.indicator;
truncate table market.ticker;
truncate table market.file;

--select values from indicator table 
select * from market.indicator where series='eq' order by symbol,timestamp



select * from market.indicator limit 5;

--

select distinct str_to_date(timestamp,'%d-%M-%Y') from market.indicator;