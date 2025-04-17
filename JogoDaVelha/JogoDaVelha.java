public class JogoDaVelha {
    protected static final int X = 1, O = -1;
    protected static final int VAZIO = 0;
    protected int tabuleiro[][];
    protected int dimensao;
    protected int jogador;

    public JogoDaVelha(int dimensao) {
        this.dimensao = dimensao; // Inicializando a dimensão
        tabuleiro = new int[dimensao][dimensao];
        limpaTabuleiro();
    }

    public void limpaTabuleiro() {
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }
        jogador = X;
    }

    public void poePeca(int i, int j) {
        if (i < 0 || i >= dimensao || j < 0 || j >= dimensao) {
            throw new IllegalArgumentException("Posição Inválida");
        }
        if (tabuleiro[i][j] != VAZIO) throw new IllegalArgumentException("Posição Ocupada");
        tabuleiro[i][j] = jogador;
        jogador = -jogador;
    }

    public boolean eVencedor(int jogador) {
        return verificaLinhas(jogador) || verificaColunas(jogador) || verificaDiagonais(jogador);
    }
    
    private boolean verificaLinhas(int jogador) {
        for (int i = 0; i < dimensao; i++) {
            if (verificaSoma(tabuleiro[i], jogador)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean verificaColunas(int jogador) {
        for (int j = 0; j < dimensao; j++) {
            int[] coluna = new int[dimensao];
            for (int i = 0; i < dimensao; i++) {
                coluna[i] = tabuleiro[i][j];
            }
            if (verificaSoma(coluna, jogador)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean verificaDiagonais(int jogador) {
        int[] diagonalPrincipal = new int[dimensao];
        int[] diagonalSecundaria = new int[dimensao];
    
        for (int i = 0; i < dimensao; i++) {
            diagonalPrincipal[i] = tabuleiro[i][i];
            diagonalSecundaria[i] = tabuleiro[i][dimensao - 1 - i];
        }
    
        return verificaSoma(diagonalPrincipal, jogador) || verificaSoma(diagonalSecundaria, jogador);
    }
    
    private boolean verificaSoma(int[] linhaOuColuna, int jogador) {
        int soma = 0;
        for (int valor : linhaOuColuna) {
            soma += valor;
        }
        return soma == jogador * dimensao;
    }
    
    public int vencedor() {
        if (eVencedor(X)) {
            return X;
        } else if (eVencedor(O)) {
            return O;
        } else {
            return 0; // Empate
        }
    }
    
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                resultado.append(tabuleiro[i][j] == X ? "X" : tabuleiro[i][j] == O ? "O" : " ");
                if (j < dimensao - 1) {
                    resultado.append(" | ");
                }
            }
            if (i < dimensao - 1) {
                resultado.append("\n" + "-".repeat(dimensao * 4 - 1) + "\n");
            }
        }
        return resultado.toString();
    }
}