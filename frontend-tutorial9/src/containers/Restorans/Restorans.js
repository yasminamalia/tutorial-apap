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
            isEdit: false,
            isLoading: true,
            isSearched: false,
            nama: "",
            alamat: "",
            nomorTelepon: "",
            rating: "",
            query: "",
            filteredRestorans: []
        }
    }

    handleInputChange = event => {
        this.setState({ isSearched: true});
        const query = event.target.value;

        this.setState(prevState  => {
            const filteredRestorans = prevState.restorans.filter(element => {
                return element.nama.toLowerCase().includes(query.toLowerCase());
            });
            return{
                query,
                filteredRestorans
            };
        });
    };

    componentDidMount() {
        this.loadRestorans();
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
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
        this.setState({ isCreate: false, isEdit: false });
    }

    changeHandler = event => {
        // name dari prop nama di-input
        const {name, value} = event.target;
        this.setState({[name]: value});
    }

    submitAddRestoranHandler = event => {
        console.log("add");
        event.preventDefault();
        this.setState({
            nama: "",
            alamat: "",
            rating: "",
            nomorTelepon: "",
            isLoading: true
        });
        this.addRestoran();
        this.canceledHandler();
        console.log("berhasil ga?");
    }

    editRestoranHandler(restoran) {
        this.setState({
            isEdit: true,
            idRestoran: restoran.idRestoran,
            nama: restoran.nama,
            nomorTelepon: restoran.nomorTelepon,
            rating: restoran.rating,
            alamat: restoran.alamat
        })
    }

    submitEditRestoranHandler = event => {
        console.log("editing")
        event.preventDefault();
        this.setState({
            nama: "",
            alamat: "",
            rating: "",
            nomorTelepon: "",
            isLoading: true
        });
        this.editRestoran();
        this.canceledHandler();
    };

    async editRestoran() {
        const restoranToEdit = {
            idRestoran: this.state.idRestoran,
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.put("/restoran/" + this.state.idRestoran, restoranToEdit);
        await this.loadRestorans();
        this.canceledHandler();
    }

    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }

    async deleteRestoranHandler(restoranId) {
        await axios.delete(`/restoran/${restoranId}`);
        await this.loadRestorans();
    }

    render() {
        console.log("render()");

        const render = this.state.isSearched ?
            <div>
                {this.state.filteredRestorans.map(restoran =>
                    <Restoran
                        key = {restoran.idRestoran}
                        nama = {restoran.nama}
                        alamat = {restoran.alamat}
                        nomorTelepon = {restoran.nomorTelepon}
                        edit = {() => this.editRestoranHandler(restoran)}
                        delete = {() => this.deleteRestoranHandler(restoran.idRestoran)}
                    />
                    )}
            </div>
            :
            <div>
                {this.state.restorans && this.state.restorans.map(restoran =>
                    <Restoran
                        key = {restoran.idRestoran}
                        nama = {restoran.nama}
                        alamat = {restoran.alamat}
                        nomorTelepon = {restoran.nomorTelepon}
                        edit = {() => this.editRestoranHandler(restoran)}
                        delete = {() => this.deleteRestoranHandler(restoran.idRestoran)}
                    />)}
            </div>

        return(
            <React.Fragment>
                <Modal show={this.state.isCreate || this.state.isEdit}
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
                <div className={classes.SearchLayout}>
                    <form>
                        <input className={classes.SearchRestoran}
                            placeholder="Cari Restoran..."
                            value={this.state.query}
                            onChange={this.handleInputChange}
                        />
                    </form>
                </div>
                <div className={classes.Restorans}>
                    {render}
                </div>
            </React.Fragment>
        );
    }

    renderForm(){
        const { isEdit } = this.state;
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
                <Button btnType="Success" onClick={isEdit ? this.submitEditRestoranHandler : this.submitAddRestoranHandler}>
                    Submit
                </Button>
            </form>
        );
    }
}

export default Restorans;