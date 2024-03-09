import defaultTheme from 'tailwindcss/defaultTheme';

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}"
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ['--font-inter', ...defaultTheme.fontFamily.sans]
      },
      colors: {
        violet: {
          light: '#6C5CCF',
          DEFAULT: '#7763C5',
        },
        gray: '#F5F5F5',
      },
    },
  },
  plugins: [
    require('@tailwindcss/aspect-ratio'),
  ],
}

