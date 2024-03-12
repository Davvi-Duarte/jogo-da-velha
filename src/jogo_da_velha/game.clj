(ns jogo-da-velha.game)

;Autor: Davvi Duarte Rodrigues

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

(defn verifica_casa_disponivel
  [tabuleiro linha casa]
  (if (not (= "." (nth (nth tabuleiro (dec linha)) (dec casa))))
    false
    true))

(defn verificar-todas-linhas-preenchidas [linhas]
  (every? #(not= "." %) (apply concat linhas)))

(defn verificar-vencedor [tabuleiro]
  (let [linhas tabuleiro
        colunas (apply map vector tabuleiro)
        diagonais [(map nth tabuleiro [0 1 2]) (map nth tabuleiro [2 1 0])]
        todas-secoes (concat linhas colunas diagonais)]
    (some #(every? (partial = %) %) todas-secoes)))

(defn mensagem_vitoria
  [jogador]
  (if (= jogador "Jogador 1")
    (println "JOGADOR 2 VENCEU!")
    (println "JOGADOR 1 VENCEU!")))

(defn game [tabuleiro rodada jogador]
  (imprimir-tabuleiro tabuleiro rodada)
  (cond
    (and (verificar-todas-linhas-preenchidas tabuleiro) (not (verificar-vencedor tabuleiro))) (println "EMPATE")
    :else (do (if (verificar-vencedor tabuleiro)
                (mensagem_vitoria jogador)
                (do (println jogador " escolha a linha que irá realizar a jogada: ")
                    (let [linha-j (read)]
                      (println jogador " escolha a casa que irá realizar a jogada: ")
                      (let [casa-j (read)]
                        (if (or (> linha-j 3) (> casa-j 3) (< linha-j 1) (< casa-j 1))
                          (do (println "Jogada inválida, tente novamente!")
                              (game tabuleiro rodada jogador))
                          (if (verifica_casa_disponivel tabuleiro linha-j casa-j)
                            (do (if (= jogador "Jogador 1")
                                  (game (insere-valor-casa tabuleiro linha-j casa-j "X") (inc rodada) "Jogador 2")
                                  (game (insere-valor-casa tabuleiro linha-j casa-j "O") (inc rodada) "Jogador 1")))
                            (do (println "Jogada inválida, tente novamente!")
                                (game tabuleiro rodada jogador)))))))))))

(def tabuleiro
  [["." "." "."]
   ["." "." "."]
   ["." "." "."]])
(game tabuleiro 1 "Jogador 1")