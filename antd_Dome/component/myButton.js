import React from 'react';
import ReactDOM from 'react-dom';
import { Button } from 'antd';

export default class MyButton extends React.Component{
render(){
      return(
        <div {...this.props}>
              <Button type="primary">lalalla</Button>
              <Button>Default</Button>
              <Button type="dashed">Dashed</Button>
              <Button type="danger">Danger</Button>
        </div>
      );
    }
}