(ns jogo-da-velha.game)

(def tabuleiro-exemplo
  [["1" "2" "3"]
   ["4" "5" "6"]
   ["7" "8" "9"]])


(defn insere-valor-casa
  "funcao para testar a iteracao dentro de matrizes"
  [tabuleiro linha casa simbolo]
  (let [tabuleiro-resposta tabuleiro
        linha-tabuleiro (nth tabuleiro (dec linha))]
    (assoc tabuleiro-resposta (dec linha) (assoc linha-tabuleiro (dec casa) (str simbolo)))
    ))

(defn imprimir-tabuleiro [tabuleiro rodada]
  (println "Rodada:" (str rodada))
  (doseq [linha tabuleiro]
    (doseq [celula linha]
      (print celula)
      (print " "))
    (println)))

(defn todos-iguais? [vetor]
  (if (some #(= "." %) vetor)
    false
    (apply = vetor))
  )


(defn verifica-elementos-iguais? [conj]
  (loop [pos 0]
    (cond
      (todos-iguais? (nth conj pos)) true
      (and (= pos (dec (count conj))) (not (todos-iguais? (nth conj pos)))) false
      :else (recur (inc pos)))))

(def tabuleiro-exemplo2
  [["." "." "."]
   ["." "." "."]
   ["." "." "."]])



(defn verificar-vencedor [tabuleiro]
  (let [linhas tabuleiro
        colunas (apply map vector tabuleiro)
        diagonais [(map nth tabuleiro [0 1 2]) (map nth tabuleiro [2 1 0])]]
    (or (verifica-elementos-iguais? linhas) (verifica-elementos-iguais? colunas) (verifica-elementos-iguais? diagonais))
    )
  )
(println (verificar-vencedor tabuleiro-exemplo2))

(defn play-jogador-2
  [tabuleiro rodada]
  (imprimir-tabuleiro tabuleiro rodada)
  (println "Jogador 2 escolha a linha que irá realizar a jogada: ")
  (let [linha-j (read)]
    (println "Jogador 2 escolha a casa que irá realizar a jogada: ")
    (let [casa-j (read)]
      (game (insere-valor-casa tabuleiro linha-j casa-j "X") (inc rodada))
      )))
(defn play-jogador-1
  [tabuleiro rodada]
  (imprimir-tabuleiro tabuleiro rodada)
  (println "Jogador 1 escolha a linha que irá realizar a jogada: ")
  (let [linha-j (read)]
    (println "Jogador 1 escolha a casa que irá realizar a jogada: ")
    (let [casa-j (read)]
      (game (insere-valor-casa tabuleiro linha-j casa-j "X") (inc rodada))
      )))

(defn game [tabuleiro rodada jogador]
  (imprimir-tabuleiro tabuleiro rodada)
  (if (= jogador 1)
    (play-jogador-1 tabuleiro rodada)
    (play-jogador-2 tabuleiro rodada))
  )

(game tabuleiro-exemplo2 0 1)