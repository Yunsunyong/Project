select *
from (SELECT ROW_NUMBER() OVER(order by parent_no desc, answer_ref asc,qna_level asc, qna_index asc) RNUM, qna_no, qna_title, qna_content, qna_date, original_qname, rename_qname, parent_no, answer_ref,qna_level, qna_index, qna_status, qna_writer  
FROM TB_QNA) QNA 
where QNA.RNUM >= 1 and QNA.RNUM <= 20;