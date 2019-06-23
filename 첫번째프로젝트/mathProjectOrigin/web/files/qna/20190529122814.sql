DELETE FROM TB_QNA 
				WHERE QNA_NO IN ( 
				SELECT QNA_NO
				FROM 
				(SELECT Q.QNA_NO 
				FROM TB_QNA Q
				WHERE Q.PARENT_NO IN (SELECT Q2.PARENT_NO
				                        FROM TB_QNA Q2
				                        WHERE Q2.QNA_NO = 6)) QQ);