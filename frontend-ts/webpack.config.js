const path = require('path');

module.exports = {
  mode: 'production',  
  entry: {
    login: ['./src/components/login.ts'],
    formularioEmpresa: ['./src/components/formularioEmpresa.ts'],
    formularioCandidato: ['./src/components/formularioCandidato.ts'],
    listaCandidatos: ['./src/components/listaCandidatos.ts'],
    listaVagas: ['./src/components/listaVagas.ts'],
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        use: 'ts-loader',
        exclude: /node_modules/,
      },
    ],
  },
  resolve: {
    extensions: ['.tsx', '.ts', '.js'],
  },
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, 'public', 'dist'),
  },
};