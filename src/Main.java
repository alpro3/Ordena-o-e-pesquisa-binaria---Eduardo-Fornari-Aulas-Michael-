import java.awt.Insets;
import java.util.Arrays;
import java.util.Random;


public class Main {
	private static long tempoIni, tempoFim;

	public static void initClock() {
		tempoIni = tempoFim = System.nanoTime();
	}

	public static double getClockSec() {
		double res;
		tempoFim = System.nanoTime();
		res = ((double)tempoFim - (double)tempoIni) / (double)1000000000.0;
		return res;
	}
	
	public static int[] geraVetor(int nroElem, int lim){
		int dummy;
		int [] res = null;
		int cont = 0;
		Random rnd = new Random(System.nanoTime() * System.currentTimeMillis());
		if (nroElem >= 0) {
			res = new int[nroElem];
			while (cont < nroElem) {
				dummy = rnd.nextInt(lim)+1;
				dummy = rnd.nextInt(lim)+1;
				res[cont++] = rnd.nextInt(lim)+1;
			}
		}
		return res;
	}
	
	public static int pesqDireta(int [] vet, int valor) {
		int res = -1, i;
		for (i = 0; ((i < vet.length) && (vet[i] != valor)); i++){
			if (i < vet.length)res = i;
		}
		return res;
	}
	
	public static int binarySearch( int [] TAB, int value ) {
		int low = 0;
		int high = TAB.length - 1;
		int p = low+((high-low)/2);
		while ( low <= high) {
			if ( TAB[p] > value )high = p - 1;
			else if ( TAB[p] < value )low = p + 1;
			else return p;
			p = low+((high-low)/2);
		}
		return -1;
	}
	
	public static void bubbleSort2(int [] v){
		for (int i=0; i<v.length; i++) {
			for (int j=0; j<v.length-(i+1); j++) {
				if (v[j] > v[j+1]){
					int aux = v[j];
					v[j] = v[i];
					v[i] = aux;
				}
			}
		}
	}
	
	public static void bubbleSort(int [] v){
		boolean ordenado = false;
		int i = 0;
		while (ordenado==false) {
			ordenado = true;
			for (int j=0; j<v.length-(i+1); j++) {
				if (v[j] > v[j+1]){
					int aux = v[j+1];
					v[j] = v[j+1];
					v[j+1] = aux;
					ordenado = false;
				}
			}
			i++;
		}
	}
	
	public static void insertionSort(int [] v){
		int i, j, chave;
		for (j=1; j<v.length; j++) {
			chave = v[j];
			i = j - 1;
			while ((i >= 0) && (v[i] > chave)) {
					v[i+1] = v[i];
					i = i-1;
			}
			v[i+1] = chave;
		}
	}
	
	public static void mergeSort(int [] v){
		mergeSort(v, 0, v.length-1);
	}
	
	public static void mergeSort(int [] v, int inicio, int fim){
		if (inicio < fim){
			int meio = (inicio + fim) / 2;
			mergeSort(v, inicio, meio);
			mergeSort(v, meio+1, fim);
			merge(v, inicio, meio, fim);
		}
	}
	
	private static void merge(int [] v, int inicio, int meio, int fim) {
		int nL = meio-inicio+1; int nR = fim-meio;
		int [] L = new int[nL]; int [] R = new int[nR];
		System.arraycopy(v, inicio, L, 0, nL);
		
		System.arraycopy(v, meio+1, R, 0, nR);
		int iL = 0; int iR = 0;
		for (int k=inicio; k<=fim; k++) {
			if (iR < nR) {
				if (iL < nL) {
					if (L[iL] < R[iR]) v[k] = L[iL++];
					else v[k] = R[iR++];
				}
				else v[k] = R[iR++];
			}
			else v[k] = L[iL++];
		}
	}

	public static void printVet(Object obj){
		int vet[] = (int[])obj;
		for (int i = 0; i < vet.length; i++) {
			System.out.print(vet[i]+" ");
		}
		System.out.println();
	}
	
	public static void quicksort(int [] v, int low, int high) {
		int pivot;
		if ((high-low) > 0) {
			pivot = partition(v, low, high);
			quicksort(v, low, pivot-1);
			quicksort(v, pivot+1, high);
		}
	}
	
	public static int partition(int [] v, int low, int high) {
		int i, p, firsthigh;
		firsthigh = low;
		p = high;
		for (i=low; i<high; i++){
			if (v[i] < v[p]) {
				swap(v, i, firsthigh);
				firsthigh = firsthigh + 1;
			}
		}
		swap(v, p, firsthigh);
		return firsthigh;
	}
	
	public static void quicksort(int v []){
		quicksort(v, 0, v.length-1);
	}
	
	public static void swap(int v[], int p, int firsthigh){
		int aux = v[p];
		v[p] = v[firsthigh];
		v[firsthigh] = aux;
	}
	
	public static void main(String[] args) {
		Object vet[] = new Object[100];
		int qtd = 1000;
		for (int i = 0; i < 5; i++) {
			vet[i] = geraVetor(qtd, 100);
			qtd = qtd*10;
		}
		for (int i = 0; i < vet.length; i++) {
			int vetAUX [] = (int [])vet[i];
			int vetAUX2 [] = (int [])vetAUX.clone();
			initClock();
			bubbleSort(vetAUX2);
			double tempo = getClockSec();
			System.out.println("Ordenando um vetor de "+vetAUX.length+" com BubbleSort: "+tempo);
			vetAUX2 = (int [])vetAUX.clone();
			initClock();
			insertionSort(vetAUX2);
			tempo = getClockSec();
			System.out.println("Ordenando um vetor de "+vetAUX.length+" com InsertionSort: "+tempo);
			vetAUX2 = (int [])vetAUX.clone();
			initClock();
			mergeSort(vetAUX2);
			tempo = getClockSec();
			System.out.println("Ordenando um vetor de "+vetAUX.length+" com MergeSort: "+tempo);
			vetAUX2 = (int [])vetAUX.clone();
			initClock();
			quicksort(vetAUX2);
			tempo = getClockSec();
			System.out.println("Ordenando um vetor de "+vetAUX.length+" com QuickSort: "+tempo);
		}
	}
}