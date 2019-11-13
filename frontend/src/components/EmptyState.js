import React from "react";

export default function EmptyState(props) {
    return(
        <>
            <h3 style={styles.heading}>Belum ada item yang dipilih</h3>
            <h4>Klik salah satu item di daftar Menu</h4>
        </>
    );
}
            const styles = {
                heading: {
                fontFamily: "courier new"
            }
            };