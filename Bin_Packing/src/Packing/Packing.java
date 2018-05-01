package Packing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Packing {

	
	public static int FirstFit(List<Integer> itens, int numberOfItens, int capacidade) {

		int NumBins = 0;
		List<Integer> BinFreeSpace = new ArrayList<Integer>(Collections.nCopies(numberOfItens, capacidade)); // numberOfItens

		for (int i = 0; i < numberOfItens; i++) {

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
				BinFreeSpace.set(NumBins, BinFreeSpace.get(NumBins) - itens.get(i));
			}
		}
		
		NumBins++;
		return (NumBins);
	}

	public static int NextFit(List<Integer> itens, int numberOfItens, int capacidade) {
		int NumBins = 0;
		int BinFreeSpace = capacidade;

		for (int i = 0; i < numberOfItens; i++) {

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
		
		NumBins++;
		return NumBins;
	}

	
}