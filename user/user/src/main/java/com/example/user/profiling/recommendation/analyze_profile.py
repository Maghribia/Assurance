import pandas as pd
from sklearn.cluster import KMeans

# Charger les données depuis MongoDB (utilisez PyMongo pour la connexion)
# Exemple de données
data = {
    "age": [25, 45, 35, 50],
    "income": [50000, 100000, 75000, 120000],
    "risk_score": [2, 5, 3, 8]
}
df = pd.DataFrame(data)

# Clustering pour segmenter les utilisateurs
kmeans = KMeans(n_clusters=3)
df['cluster'] = kmeans.fit_predict(df[['age', 'income', 'risk_score']])

# Sauvegarder les résultats dans un fichier ou les renvoyer à l'API
df.to_csv("profiles_analysis.csv", index=False)