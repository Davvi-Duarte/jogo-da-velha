(ns jogo-da-velha.core)

(def tabuleiro-exemplo
  [["1" "2" "3"]
   ["4" "5" "6"]
   ["7" "8" "9"]])
=

(defn insere-valor-casa
  "funcao para testar a iteracao dentro de matrizes"
  [tabuleiro linha casa]
  (let [tabuleiro-resposta tabuleiro
        linha-tabuleiro (nth tabuleiro (dec linha))]
    (assoc tabuleiro-resposta (dec linha) (assoc linha-tabuleiro (dec casa) "X"))
    ))

(defn imprimir-tabuleiro [tabuleiro]
  (println "Rodada:"(str 0))
  (doseq [linha tabuleiro]
    (doseq [celula linha]
      (print celula)
      (print " "))
    (println)))



(imprimir-tabuleiro tabuleiro-exemplo)

(insere-valor-casa tabuleiro-exemplo 2 3)