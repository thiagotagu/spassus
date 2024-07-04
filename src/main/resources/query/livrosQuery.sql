SELECT  L.id,ANO_PUBLICACAO,EDICAO,EDITORA,TITULO,ID_AUTOR,ID_ASSUNTO,NOME,DESCRICAO FROM LIVRO L 
		        		 JOIN AUTOR A ON A.ID=L.ID_AUTOR  
		        	 JOIN ASSUNTO S ON S.ID =L.ID_ASSUNTO

-- Script para criação da view vwLivros
CREATE VIEW vwLivros AS
SELECT
    L.id AS livro_id,
    L.ANO_PUBLICACAO,
    L.EDICAO,
    L.EDITORA,
    L.TITULO,
    L.ID_AUTOR,
    L.ID_ASSUNTO,
    A.NOME AS autor_nome,
    S.DESCRICAO AS assunto_descricao
FROM
    LIVRO L
JOIN
    AUTOR A ON A.ID = L.ID_AUTOR
JOIN
    ASSUNTO S ON S.ID = L.ID_ASSUNTO;