import React from 'react';
import List from "./components/List";
import dummyItems from "./items.json";

export default class App extends React.Component {
  state = {
    favItems: [],
      visibility: true
  };

  handleItemMenu = item => {
    const newItems = [...this.state.favItems];
    const newItem = {...item};
    newItems.push(newItem);
    // schedule setState to update state with new favItems
    this.setState({favItems: newItems});
  };

  handleItemFav = item => {
      const newItems = [...this.state.favItems];
      const newItem = {...item};
      // find index of item with id
      const targetInd = newItems.findIndex(it => it.id === newItem.id);
      newItems.splice(targetInd, 1);
      // schedule setState to update state with new favItems
      this.setState({favItems: newItems});
  }

  toggleFavorite = ( )=> {this.setState((prevState)=>({visibility: !prevState.visibility}));}
  render() {
    const {favItems} = this.state;
    return(
        <div className="container-fluid">
          <h1 className="text-center">
              Welcome!
            <small>Class-based</small>
          </h1>

            <input type="checkbox" onClick={this.toggleFavorite}/>Show My Favorites

          <div className="container pt-3">
            <div className="row">
              <div className="col-sm">
                <List
                  title="Our Menu"
                  items={dummyItems}
                  onItemClick={this.handleItemMenu}
                  />
              </div>

              <div className="col-sm">
                  {!this.state.visibility &&
                  <List
                      title="My Favorites"
                      items={favItems}
                      onItemClick={this.handleItemFav}
                  />
                  }
              </div>
            </div>
          </div>
        </div>
    );
  }
};
