import React, { Component } from 'react';
import classes from './Restorans.module.css';
import Restoran from "../../components/Restoran/Restoran";
import axios from '../../axios-restoran';
import Modal from "../../components/UI/Modal/Modal";
import Button from "../../components/UI/Button/Button";

class Restorans extends Component{
    constructor(props){
        super(props);
        this.state = {
            restorans: [],
            isCreate: false,
            // isEdit: false,
            isLoading: true,
            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: ""
        }
    }

    componentDidMount() {
        this.loadRestorans();
    }

    loadRestorans = async () => {
        const fetchedRestorans = [];
        const response = await axios.get("/restorans");
        for (let key in response.data) {
            fetchedRestorans.push({
                ...response.data[key]
            });
        }

        this.setState({
            restorans: fetchedRestorans
        });
    }

    addRestoranHandler = () => {
        this.setState({ isCreate: true });
    }

    canceledHandler = () => {
        this.setState({ isCreate: false });
    }

    changeHandler = event => {
        // name dari prop nama di-input
        const {name, value} = event.target;
        this.setState({[name]: value});
    }

    submitAddRestoranHandler = event => {
        event.prevenetDefault();
        this.setState({ isLoading: true });
        this.addRestoran();
        this.canceledHandler();
    }

    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.name,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }


    render() {
        console.log("render()");
        return(
            <React.Fragment>
                <Modal show={this.state.isCreate}
                       modalClosed={this.canceledHandler}>
                    {this.renderForm()}
                </Modal>
                <div className={classes.Title}> All Restorans </div>
                <div className={classes.ButtonLayout}>
                    <button
                        className={classes.AddRestoranButton}
                        onClick={this.addRestoranHandler}
                    >
                        + Add New Restoran
                    </button>
                </div>
                <div className={classes.Restorans}>
                    {this.state.restorans &&
                    this.state.restorans.map(restoran =>
                    <Restoran
                        key = {restoran.id}
                        nama = {restoran.nama}
                        alamat = {restoran.alamat}
                        nomorTelepon = {restoran.nomorTelepon}
                    />
                    )}
                </div>
            </React.Fragment>
        );
    }

    renderForm(){
        return(
            <form>
                <input
                className={classes.Input}
                name="nama"
                type="text"
                placeholder="Nama"
                value={this.state.nama}
                onChange={this.changeHandler}
                />

                <input
                    className={classes.Input}
                    name="nomorTelepon"
                    type="number"
                    placeholder="Nomor Telepon"
                    value={this.state.nomorTelepon}
                    onChange={this.changeHandler}
                />

                <input
                    className={classes.TextArea}
                    name="alamat"
                    type="text"
                    placeholder="Alamat"
                    value={this.state.alamat}
                    onChange={this.changeHandler}
                />

                <input
                    className={classes.Input}
                    name="rating"
                    type="number"
                    placeholder="Rating"
                    value={this.state.rating}
                    onChange={this.changeHandler}
                />
                <Button btnType="Danger" onClick={this.canceledHandler}> Cancel </Button>
                <Button btnType="Success" onClick={this.submitAddRestoranHandler}> Submit </Button>
            </form>
        );
    }
}

export default Restorans;