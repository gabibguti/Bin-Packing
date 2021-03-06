package Packing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Packing {

	
	public static int FirstFit_GN(List<Integer> itens, int numberOfItens, int capacidade) {

		int NumBins = 0;
		List<Integer> BinFreeSpace = new ArrayList<Integer>(Collections.nCopies(numberOfItens, capacidade)); // numberOfItens
		
		if (itens.isEmpty() == false) {
			BinFreeSpace.set(NumBins, BinFreeSpace.get(NumBins) - itens.get(0));
			NumBins++;
		}
		
		for (int i = 1; i < numberOfItens; i++) {
			
			int j;
			
			for (j = 0; j < NumBins; j++) {

				// Insere o item[i] no Bin[j], se houver espaco
				if (BinFreeSpace.get(j) >= itens.get(i)) {
					BinFreeSpace.set(j, BinFreeSpace.get(j) - itens.get(i));
					break;
				}
			}

			// Se nao houver onde inserir o item[i], acrescenta mais um bin e o insere
			if (j == NumBins) {
				NumBins++;
				BinFreeSpace.set(j, BinFreeSpace.get(j) - itens.get(i));
			}
		}
		return (NumBins);
	}
	
	public static List<List<Integer>> FirstFit_GN_Saida(List<Integer> itens, int numberOfItens, int capacidade) {

		int NumBins = 0;
		List<Integer> BinFreeSpace = new ArrayList<Integer>(Collections.nCopies(numberOfItens, capacidade)); // numberOfItens
		
		// Variavel que vai guardar os melhores resultados numa matriz de tamanho igual ao numero
		// de elementos no formato [ [Nbin , item1] , [Nbin , item2] ... ]
		Integer[][] tuplas = new Integer[numberOfItens][2]; 
		
		if (itens.isEmpty() == false) {
			BinFreeSpace.set(NumBins, BinFreeSpace.get(NumBins) - itens.get(0));
			NumBins++;
			tuplas[0][0] = NumBins++;
			tuplas[0][1] = itens.get(0);
		}
		
		for (int i = 1; i < numberOfItens; i++) {
			
			int j;
			
			for (j = 0; j < NumBins; j++) {

				// Insere o item[i] no Bin[j], se houver espaco
				if (BinFreeSpace.get(j) >= itens.get(i)) {
					BinFreeSpace.set(j, BinFreeSpace.get(j) - itens.get(i));
					tuplas[i][0] = j+1;
					tuplas[i][1] = itens.get(i);
					break;
				}
			}

			// Se nao houver onde inserir o item[i], acrescenta mais um bin e o insere
			if (j == NumBins) {
				NumBins++;
				BinFreeSpace.set(j, BinFreeSpace.get(j) - itens.get(i));
				tuplas[i][0] = j+1;
				tuplas[i][1] = itens.get(i);
			}
		}
		
		List<List<Integer>> ListOfCompletedBins = new ArrayList<List<Integer>>();        
        
        for(int i=0; i < NumBins; i++) {
        	//Inicializa todo o ArrayList de ArrayLists
        	ListOfCompletedBins.add(new ArrayList<Integer>());
        }
        for(int i=0; i < tuplas.length; i++) {
        	int binNUMBER = tuplas[i][0];
        	int elemento = tuplas[i][1];
        	ListOfCompletedBins.get(binNUMBER-1).add(elemento);
        }		
		return (ListOfCompletedBins);
	}

	public static int NextFit_GN(List<Integer> itens, int numberOfItens, int capacidade) {
		int NumBins = 0;
		int BinFreeSpace = capacidade;

		if (itens.isEmpty() ==	 false) {
			BinFreeSpace -= itens.get(0);
			NumBins++;
		}
		
		for (int i = 1; i < numberOfItens; i++) {

			// Se houver espaco, insere o item[i] no ultimo Bin alocado
			if (BinFreeSpace >= itens.get(i)) {
				BinFreeSpace -= itens.get(i);
			}

			// Se nao houver onde inserir o item[i], acrescenta mais um bin e o insere
			else {
				NumBins++;
				BinFreeSpace = capacidade - itens.get(i);
			}
		}
		return NumBins;
	}
	
	public static List<List<Integer>> NextFit_GN_Saida(List<Integer> itens, int numberOfItens, int capacidade) {
		int NumBins = 0;
		int BinFreeSpace = capacidade;

		// Variavel que vai guardar os melhores resultados numa matriz de tamanho igual ao numero
		// de elementos no formato [ [Nbin , item1] , [Nbin , item2] ... ]
		Integer[][] tuplas = new Integer[numberOfItens][2];
		
		if (itens.isEmpty() ==	 false) {
			BinFreeSpace -= itens.get(0);
			NumBins++;
			tuplas[0][0] = NumBins;
			tuplas[0][1] = itens.get(0);
		}
		
		for (int i = 1; i < numberOfItens; i++) {

			// Se houver espaco, insere o item[i] no ultimo Bin alocado
			if (BinFreeSpace >= itens.get(i)) {
				BinFreeSpace -= itens.get(i);
				tuplas[i][0] = NumBins;
				tuplas[i][1] = itens.get(i);
			}

			// Se nao houver onde inserir o item[i], acrescenta mais um bin e o insere
			else {
				NumBins++;
				BinFreeSpace = capacidade - itens.get(i);
				tuplas[i][0] = NumBins;
				tuplas[i][1] = itens.get(i);
			}
		}
		
		List<List<Integer>> ListOfCompletedBins = new ArrayList<List<Integer>>();        
        
        for(int i=0; i < NumBins; i++) {
        	//Inicializa todo o ArrayList de ArrayLists
        	ListOfCompletedBins.add(new ArrayList<Integer>());
        }
        for(int i=0; i < tuplas.length; i++) {
        	int binNUMBER = tuplas[i][0];
        	int elemento = tuplas[i][1];
        	ListOfCompletedBins.get(binNUMBER-1).add(elemento);
        }		
		return (ListOfCompletedBins);
	}

	public static List<List<Integer>> FirstFit_LS (List<Integer> items, int binMaxCapacity) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		Boolean fitted;
		
		// Para cada item nos items
		for(Integer item: items) {
			// Inicializa fitted como "nao encaixou em nenhum bin"
			fitted = false;
			// Procura um bin em que o item encaixa
			for(List<Integer> bin: solution) {
				Integer totalWeight = bin.stream().mapToInt(Integer::intValue).sum();
				if(totalWeight + item <= binMaxCapacity) {
					// Ao encontrar um bin em que o item encaixa, adiciona ele ao bin
					// e muda fitted para "encaixou em algum bin"
					bin.add(item);
					fitted = true;
					break;
				}
			}
			// Se ao final da procura o item "nao encaixou em nenhum bin"
			if(fitted == false) {
				// Adiciona um novo bin e coloca o item
				List<Integer> newBin = new ArrayList<Integer>();
				newBin.add(item);
				solution.add(newBin);
			}
		}
		
		return solution;
	}
	
	public static List<List<Integer>> Realocate_LS (List<List<Integer>> solution, int binMaxCapacity) {
		
		List<List<Integer>> better_solution = Packing.cloneStructure(solution);
		List<List<Integer>> aux1;
		List<List<Integer>> aux2;
		Boolean retry = true;
		Integer last = better_solution.size();
		
		while(retry) {
			aux1 = cloneStructure(better_solution);
			aux2 = cloneStructure(better_solution);
			// Ordena os bins por ordem crescente de peso / ordem descrescente de espaço livre
			Collections.sort(aux1, new WeightComparator());
			// Ordena os bins por ordem decrescente de peso / ordem crescente de espaço livre
			Collections.sort(aux2, new ReverseWeightComparator());
			
			search: {
				// Para todos os bins começando do que tem mais espaço livre para o que tem menos espaço livre
				for(List<Integer> bin1: aux1) {
					// Para todos os itens desse bin
					for(Integer itemInBin1: bin1) {
						// Para todos os bins começando do que está mais cheio para o que está mais vazio
						for(List<Integer> bin2: aux2) {
							// Confere se nao é o mesmo bin
							if(bin1 != bin2) {
								Integer weightInBin2 = bin2.stream().mapToInt(Integer::intValue).sum();
								// Tenta encaixar o item do bin mais vazio no bin mais cheio
								if((weightInBin2 + itemInBin1) <= binMaxCapacity) {
									List<Integer> Bin1InSolution = better_solution.get(better_solution.indexOf(bin1));
									List<Integer> Bin2InSolution = better_solution.get(better_solution.indexOf(bin2));
									Bin1InSolution.remove(Integer.valueOf(itemInBin1));
									if(Bin1InSolution.size() == 0) {
										better_solution.remove(Bin1InSolution);
									}
									Bin2InSolution.add(itemInBin1);
									
									if(better_solution.size() < last) {
										last = better_solution.size();
									} else {
										retry = false;
									}
									break search;
								}
							}
						}
					}
				}
				
				retry = false;
			}
		}
		
		return better_solution;
	}
	
	public static List<List<Integer>> cloneStructure(List<List<Integer>> list) {
		List<List<Integer>> list_clone = new ArrayList<List<Integer>>();
		for(List<Integer> element: list) {
			List<Integer> element_clone = new ArrayList<Integer>();
			for(Integer i: element) {
				element_clone.add(i);
			}
			list_clone.add(element_clone);
		}
		return list_clone;
	}
	
	// Compara o peso dos bins (menor ganha)
	public static class WeightComparator implements Comparator<List<Integer>> {
	    @Override
	    public int compare(List<Integer> o1, List<Integer> o2) {
	    	Integer weight_o1 = o1.stream().mapToInt(Integer::intValue).sum();
	    	Integer weight_o2 = o2.stream().mapToInt(Integer::intValue).sum();
	        return weight_o1.compareTo(weight_o2);
	    }
	}

	// Compara o peso dos bins (maior ganha)
	public static class ReverseWeightComparator implements Comparator<List<Integer>> {
	    @Override
	    public int compare(List<Integer> o1, List<Integer> o2) {
	    	Integer weight_o1 = o1.stream().mapToInt(Integer::intValue).sum();
	    	Integer weight_o2 = o2.stream().mapToInt(Integer::intValue).sum();
	        return -weight_o1.compareTo(weight_o2);
	    }
	}
	
}