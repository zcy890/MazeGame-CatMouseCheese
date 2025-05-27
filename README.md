# Maze Game

A web-based maze game where players control a mouse to collect cheese while avoiding cats. Built with Java Spring Boot backend and Vue.js frontend.

## 🎮 Game Overview

Navigate through a procedurally generated maze as a mouse, collecting cheese while avoiding three roaming cats. The game features fog-of-war mechanics where only nearby areas are visible, adding an element of strategy and suspense.

### Game Features

- **Procedurally Generated Mazes**: Each game creates a unique 20x15 maze using backtracking algorithm
- **Fog of War**: Limited visibility around the player character
- **Dynamic Enemies**: Three cats that move randomly through the maze
- **Progressive Reveal**: Areas become permanently visible once visited
- **Cheese Collection**: Collect 5 pieces of cheese to win
- **Cheat Modes**: Built-in cheats for testing and fun

## 🚀 Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6+
- Modern web browser

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/zcy890/MazeGame-CatMouseCheese.git
   cd MazeGame-CatMouseCheese
   ```

2. **Build and run the backend**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the game**
   Open your browser and navigate to `http://localhost:8080`

## 🎯 How to Play

### Controls
- **Arrow Keys**: Move the mouse up, down, left, right
- **New Game Button**: Start a fresh game
- **Cheat Buttons**: 
  - *1 Cheese*: Reduce winning condition to just 1 cheese
  - *Show All*: Reveal the entire maze
  - *Force Cat Move*: Manually trigger cat movement

### Objective
- Navigate the maze to collect 5 pieces of cheese
- Avoid the three cats roaming the maze
- Win by collecting all required cheese
- Lose if a cat catches you

### Game Mechanics
- Only areas within a 3x3 grid around your position are visible
- Previously visited areas remain visible
- Cats move randomly and reveal/hide areas as they move
- Cheese appears randomly in non-wall locations
- New cheese spawns automatically when collected

## 🏗️ Architecture

### Backend (Java Spring Boot)
```
src/main/java/ca/cmpt213/as4/
├── Application.java                 # Main Spring Boot application
├── controllers/
│   └── MazeGameController.java     # REST API endpoints
├── model/
│   ├── GameManager.java            # Game logic coordinator
│   ├── Maze.java                   # Maze generation and management
│   ├── Mouse.java                  # Player character
│   ├── Cat.java                    # Enemy entities
│   ├── Cheese.java                 # Collectible items
│   ├── Cell.java                   # Individual maze cells
│   ├── Point.java                  # 2D coordinate system
│   └── Direction.java              # Movement directions
└── restapi/                        # API wrapper classes
    ├── ApiGameWrapper.java
    ├── ApiBoardWrapper.java
    └── ApiLocationWrapper.java
```

### Frontend (Vue.js)
```
public/
├── index.html                      # Main HTML structure
├── scripts/
│   └── game.js                    # Vue.js application logic
└── css/
    └── game.css                   # Styling and layout
```

## 🔧 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/about` | Get author information |
| `POST` | `/api/games` | Create a new game |
| `GET` | `/api/games` | Get all games |
| `GET` | `/api/games/{id}` | Get specific game state |
| `GET` | `/api/games/{id}/board` | Get game board state |
| `POST` | `/api/games/{id}/moves` | Make a move (MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, MOVE_CATS) |
| `POST` | `/api/games/{id}/cheatstate` | Activate cheat (1_CHEESE, SHOW_ALL) |

## 🎨 Technical Features

### Maze Generation
- Uses recursive backtracking algorithm
- Ensures all areas are reachable
- Prevents 2x2 open areas for challenging gameplay

### Game State Management
- RESTful API design
- Atomic game operations
- Proper error handling and HTTP status codes

### Frontend Architecture
- Vue.js reactive data binding
- Axios for HTTP requests
- Keyboard event handling
- Dynamic sprite rendering with CSS positioning

## 🐛 Error Handling

The game includes comprehensive error handling:
- **400 Bad Request**: Invalid moves or parameters
- **404 Not Found**: Non-existent game IDs
- **Sound Effects**: Audio feedback for wall collisions

## 🎵 Assets

- Game sprites from the Crystal Project icon set by Everaldo Coelho (LGPL license)
- Sound effects for game interactions
- Responsive CSS layout with sidebar navigation

## 🔮 Future Enhancements

Potential improvements could include:
- Multiple difficulty levels
- High score tracking
- Multiplayer support
- Custom maze sizes
- Power-ups and special abilities
- Mobile-responsive touch controls

## 📝 License

This project uses icons from the Crystal Project, licensed under LGPL. Please refer to the original license for usage terms.

## 👤 Author

**Zecheng Yan**

---

*Built as part of CMPT 213 coursework - demonstrating full-stack web development with Java Spring Boot and Vue.js*
