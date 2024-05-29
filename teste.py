import requests

# Dados do grafo para enviar para a função no Google Cloud Function
dados = {
    "grafo": [[1, 2, 3], [0, 3, 4], [0, 5, 6], [0, 1, 7], [1, 5], [2, 4, 6, 7], [2, 5], [3, 5]],
    "origem": 5  # Starting vertex for BFS traversal
}

# URL da Cloud Function
url_cloud_function = "https://southamerica-east1-hidden-marker-424618-j7.cloudfunctions.net/Busca_em_largura"  # Substitua pelo URL da sua Cloud Function

try:
    # Envia a solicitação HTTP POST para a função Cloud Function
    resposta = requests.post(url_cloud_function, json=dados)

    # Verifica se a solicitação foi bem-sucedida
    if resposta.status_code == 200:
        # Extrai e imprime o resultado da busca em largura
        resultado = resposta.json().get("resultado")
        print("Resultado da busca em largura:", resultado)
    else:
        print("Erro ao enviar a requisição:", resposta.text)
except Exception as e:
    print("Erro ao enviar a requisição:", str(e))
