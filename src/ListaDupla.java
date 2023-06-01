import java.util.Random;
import java.lang.Math;

public class ListaDupla {
    Celula Primeira;
    Celula Ultima;
    int TotaldeElementos = 0;

    boolean PosicaoOcupada(int pos){
        return((pos >= 0)&&(pos < this.TotaldeElementos));
        }
    
    Celula PegaCelula(int pos){
        if(!this.PosicaoOcupada(pos)){
            throw new IllegalArgumentException("Posicao nao existe");    
        }else{
            Celula atual = this.Primeira;
            for(int i=0; i < pos; i++){
            atual = atual.getProxima();
            }
            return(atual);
            } 
        }

    Object Pega(int pos){
        return(this.PegaCelula(pos).getElemento());
        }        

    void AdicionaNoComeco(Object elemento){
        if(this.TotaldeElementos == 0){
            Celula nova = new Celula(elemento);
            this.Primeira = nova;
            this.Ultima = nova;
        }else{
            Celula nova = new Celula(this.Primeira, elemento);
            this.Primeira.setAnterior(nova);
            this.Primeira = nova;
        }
        this.TotaldeElementos++; }

    void Adiciona(Object elemento){
        if(this.TotaldeElementos == 0){
            this.AdicionaNoComeco(elemento);
        }else{
            Celula nova = new Celula(elemento);
            this.Ultima.setProxima(nova);
            nova.setAnterior(this.Ultima);
            this.Ultima = nova;
            this.TotaldeElementos++;
            } 
        }

    void Adiciona(int pos, Object elemento){
        if(pos == 0){
            this.AdicionaNoComeco(elemento);
        }else if(pos == this.TotaldeElementos){
            this.Adiciona(elemento);
        }else {
            Celula anterior = this.PegaCelula(pos-1);
            Celula proxima = anterior.getProxima();
            Celula nova = new Celula(anterior.getProxima(),elemento);
            nova.setAnterior(anterior);
            anterior.setProxima(nova);
            proxima.setAnterior(nova);
            this.TotaldeElementos++;
            } 
        }

    void RemovedoComeco(){
        if(!this.PosicaoOcupada(0)){
            throw new IllegalArgumentException("Posicao nao Existe");
        }else{
            this.Primeira = this.Primeira.getProxima();
            this.TotaldeElementos--;
            }
        if(this.TotaldeElementos == 0){
            this.Ultima = null;
            } 
        }

    void RemovedoFim(){
        if(!this.PosicaoOcupada(TotaldeElementos-1)){
            throw new IllegalArgumentException("Posicao nao existe");
        }else{
            if(this.TotaldeElementos == 1){
            this.RemovedoComeco();
        }else{
                Celula penultima = this.Ultima.getAnterior();
                penultima.setProxima(null);
                this.Ultima = penultima;
                this.TotaldeElementos--;
            } 
        } 
    }

    void Remove(int pos){
        if(!this.PosicaoOcupada(pos)){
            throw new IllegalArgumentException("Posicao nao Existe");
        }else{
            if (pos == 0){
                this.RemovedoComeco();
            }else if(pos == this.TotaldeElementos -1){
                this.RemovedoFim();
            }else {
                Celula anterior = this.PegaCelula(pos -1);
                Celula atual = anterior.getProxima();
                Celula proxima = atual.getProxima();
                anterior.setProxima(proxima);
                proxima.setAnterior(anterior);
                this.TotaldeElementos--;
                } 
            }
        }

    boolean Contem(Object elemento){
        Celula atual = this.Primeira;
        while(atual != null){
            if(atual.getElemento().equals(elemento)){
            return(true);
            }
        atual = atual.getProxima();
        }
        return(false);
        }

    int tamanho(){
        return(this.TotaldeElementos);
        }

    void EsvaziaLista(){
        this.Primeira = null;
        this.Ultima = null;
        this.TotaldeElementos = 0;
        }

    String Imprimir(){
        if(this.TotaldeElementos == 0){
            return("[]");
        }else{
            StringBuilder builder = new StringBuilder("[");
            Celula atual = this.Primeira;
        for(int i=0; i < this.TotaldeElementos -1; i++){
            builder.append(atual.getElemento());
            builder.append(", ");
            atual = atual.getProxima();
            }
            builder.append(atual.getElemento());
            builder.append("]");
            return(builder.toString());
            } 
        }

        void gerar1000Numeros() {
            ListaDupla lista = new ListaDupla();
            Random gerador = new Random();
            int vetor[] = new int[1000];
        
            System.out.println("Vetor:");
            for (int i = 0; i < 1000; i++) {
                int sorteado = gerador.nextInt(9999 - (-9999) + 1) + (-9999);
                vetor[i] = sorteado;
                System.out.println(vetor[i]);
        
                boolean inserido = false;
                for (int j = 0; j < lista.tamanho(); j++) {
                    if ((int) lista.Pega(j) > vetor[i]) {
                        lista.Adiciona(j, vetor[i]);
                        inserido = true;
                        break;
                    }
                }
                if (!inserido) {
                    lista.Adiciona(vetor[i]);
                }
            }
        
            System.out.println("Lista em ordem crescente:");
            System.out.println(lista.Imprimir());
        
            System.out.println("Lista em ordem decrescente:");
            for (int i = lista.tamanho() - 1; i >= 0; i--) {
                System.out.println(lista.Pega(i));
            }
            
            lista.removerPrimos();
            
            System.out.println("Lista após remover números primos:");
            System.out.println(lista.Imprimir());
        }

        void removerPrimos() {
            ListaDupla primosRemovidos = new ListaDupla();
            Celula atual = this.Primeira;
            
            while (atual != null) {
                int num = (int) atual.getElemento();
                boolean ehPrimo = true;
                
                for (int i = 2; i <= Math.sqrt(num); i++) {
                    if (num % i == 0) {
                        ehPrimo = false;
                        break;
                    }
                }
                
                if (ehPrimo) {
                    primosRemovidos.Adiciona(num);
                    Celula anterior = atual.getAnterior();
                    Celula proxima = atual.getProxima();
                    
                    if (anterior != null) {
                        anterior.setProxima(proxima);
                    } else {
                        this.Primeira = proxima;
                    }
                    
                    if (proxima != null) {
                        proxima.setAnterior(anterior);
                    } else {
                        this.Ultima = anterior;
                    }
                    
                    this.TotaldeElementos--;
                }
                
                atual = atual.getProxima();
            }
        }
    }
