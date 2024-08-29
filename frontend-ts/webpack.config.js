const path = require('path');

module.exports = {
  mode: 'production',  
  entry: {
    login: ['./src/components/login.ts'],
    formularioEmpresa: ['./src/components/formularioEmpresa.ts'],
    formularioCandidato: ['./src/components/formularioCandidato.ts'],
    perfilEmpresa: ['./src/components/perfilEmpresa.ts'],
    perfilCandidato: ['./src/components/perfilCandidato.ts'],
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