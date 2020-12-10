# 关于DocRED

(1) DocRED annotates both named entities and relations, and is the largest human-annotated dataset for document-level RE from plain text; 
(2) DocRED requires reading multiple sentences in a document to extract en- tities and infer their relations by synthesizing all information of the document; 
(3) along with the human-annotated data, we also offer large-scale distantly supervised data, which enables DocRED to be adopted for both supervised and weakly supervised scenarios.


许多已有的工作关注于句子级别的RE
文档级别的RE更难
以前的研究有不足：BC5CDR 为具体领域（PubMed，biomedicine）
对此提出了DocRED

Our human-annotated data is collected in four stages: 
使用Wikipedia文档生成 远程监督标注
(1) Generating distantly supervised annotation for Wikipedia documents. 
标注所有文档中的实体和共指信息
(2) Annotating all named entity mentions in the documents and coreference information.
和Wikidata中的条目链接命名实体
(3) Linking named entity mentions to Wikidata items. 
标注关系和相应的支持证据
(4) Labeling relations and corresponding supporting evidence.

标注时

1. 使用NER models 
2. 手工修改，训练标注员
3. 检查

Distantly Supervised Data Collection

Named entity mentions are reidentified using Bidirectional Encoder Representations from Transformers (BERT) .
Relations between each merged entity pair are labeled via distant supervision.
Wrong labeling problem


Benchmark Settings

1. rich resasoning skills （complex reasoning skills）
2. high computational cost of modeling long documents and massive entity pairs（19.5 per doc）

Treat relation prediction as a multi-label classification problem